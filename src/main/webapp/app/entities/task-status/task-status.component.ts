import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITaskStatus } from '@/shared/model/task-status.model';

import TaskStatusService from './task-status.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TaskStatus extends Vue {
  @Inject('taskStatusService') private taskStatusService: () => TaskStatusService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public taskStatuses: ITaskStatus[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTaskStatuss();
  }

  public clear(): void {
    this.retrieveAllTaskStatuss();
  }

  public retrieveAllTaskStatuss(): void {
    this.isFetching = true;
    this.taskStatusService()
      .retrieve()
      .then(
        res => {
          this.taskStatuses = res.data;
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

  public prepareRemove(instance: ITaskStatus): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTaskStatus(): void {
    this.taskStatusService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.taskStatus.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTaskStatuss();
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
