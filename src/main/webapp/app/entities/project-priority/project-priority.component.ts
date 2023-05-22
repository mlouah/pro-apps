import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProjectPriority } from '@/shared/model/project-priority.model';

import ProjectPriorityService from './project-priority.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ProjectPriority extends Vue {
  @Inject('projectPriorityService') private projectPriorityService: () => ProjectPriorityService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public projectPriorities: IProjectPriority[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProjectPrioritys();
  }

  public clear(): void {
    this.retrieveAllProjectPrioritys();
  }

  public retrieveAllProjectPrioritys(): void {
    this.isFetching = true;
    this.projectPriorityService()
      .retrieve()
      .then(
        res => {
          this.projectPriorities = res.data;
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

  public prepareRemove(instance: IProjectPriority): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProjectPriority(): void {
    this.projectPriorityService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.projectPriority.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllProjectPrioritys();
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
