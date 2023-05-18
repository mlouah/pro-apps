import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IProject } from '@/shared/model/project.model';
import ProjectService from './project.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ProjectDetails extends mixins(JhiDataUtils) {
  @Inject('projectService') private projectService: () => ProjectService;
  @Inject('alertService') private alertService: () => AlertService;

  public project: IProject = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectId) {
        vm.retrieveProject(to.params.projectId);
      }
    });
  }

  public retrieveProject(projectId) {
    this.projectService()
      .find(projectId)
      .then(res => {
        this.project = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
