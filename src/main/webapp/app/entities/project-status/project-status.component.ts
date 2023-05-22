import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProjectStatus } from '@/shared/model/project-status.model';

import ProjectStatusService from './project-status.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ProjectStatus extends Vue {
  @Inject('projectStatusService') private projectStatusService: () => ProjectStatusService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public projectStatuses: IProjectStatus[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProjectStatuss();
  }

  public clear(): void {
    this.retrieveAllProjectStatuss();
  }

  public retrieveAllProjectStatuss(): void {
    this.isFetching = true;
    this.projectStatusService()
      .retrieve()
      .then(
        res => {
          this.projectStatuses = res.data;
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

  public prepareRemove(instance: IProjectStatus): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProjectStatus(): void {
    this.projectStatusService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.projectStatus.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllProjectStatuss();
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
