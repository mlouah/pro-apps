import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProjectStatusCode } from '@/shared/model/project-status-code.model';

import ProjectStatusCodeService from './project-status-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ProjectStatusCode extends Vue {
  @Inject('projectStatusCodeService') private projectStatusCodeService: () => ProjectStatusCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public projectStatusCodes: IProjectStatusCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProjectStatusCodes();
  }

  public clear(): void {
    this.retrieveAllProjectStatusCodes();
  }

  public retrieveAllProjectStatusCodes(): void {
    this.isFetching = true;
    this.projectStatusCodeService()
      .retrieve()
      .then(
        res => {
          this.projectStatusCodes = res.data;
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

  public prepareRemove(instance: IProjectStatusCode): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProjectStatusCode(): void {
    this.projectStatusCodeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.projectStatusCode.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllProjectStatusCodes();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
