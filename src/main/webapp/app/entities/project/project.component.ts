import { mixins } from 'vue-class-component';
import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProject } from '@/shared/model/project.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import ProjectService from './project.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Project extends mixins(JhiDataUtils) {
  @Inject('projectService') private projectService: () => ProjectService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public projects: IProject[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProjects();
  }

  public clear(): void {
    this.retrieveAllProjects();
  }

  public retrieveAllProjects(): void {
    this.isFetching = true;
    this.projectService()
      .retrieve()
      .then(
        res => {
          this.projects = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IProject): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProject(): void {
    this.projectService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.project.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllProjects();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  // formatPrice
  public formatPrice(value, precision) {
    let val = (value / 1).toFixed(precision).replace('.', ',');
    return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ' ');
  }

  public getShortContent(str, num) {
    if (typeof str === 'string') {
      return str.substring(0, num) + ' ...';
    }
    return str;
  }
  // end class
}
