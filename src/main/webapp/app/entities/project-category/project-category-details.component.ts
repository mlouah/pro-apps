import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProjectCategory } from '@/shared/model/project-category.model';
import ProjectCategoryService from './project-category.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ProjectCategoryDetails extends Vue {
  @Inject('projectCategoryService') private projectCategoryService: () => ProjectCategoryService;
  @Inject('alertService') private alertService: () => AlertService;

  public projectCategory: IProjectCategory = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectCategoryId) {
        vm.retrieveProjectCategory(to.params.projectCategoryId);
      }
    });
  }

  public retrieveProjectCategory(projectCategoryId) {
    this.projectCategoryService()
      .find(projectCategoryId)
      .then(res => {
        this.projectCategory = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
