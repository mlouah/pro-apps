import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProjectCategory } from '@/shared/model/project-category.model';

import ProjectCategoryService from './project-category.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ProjectCategory extends Vue {
  @Inject('projectCategoryService') private projectCategoryService: () => ProjectCategoryService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public projectCategories: IProjectCategory[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProjectCategorys();
  }

  public clear(): void {
    this.retrieveAllProjectCategorys();
  }

  public retrieveAllProjectCategorys(): void {
    this.isFetching = true;
    this.projectCategoryService()
      .retrieve()
      .then(
        res => {
          this.projectCategories = res.data;
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

  public prepareRemove(instance: IProjectCategory): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProjectCategory(): void {
    this.projectCategoryService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.projectCategory.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllProjectCategorys();
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
