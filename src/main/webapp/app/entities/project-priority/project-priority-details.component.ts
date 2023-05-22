import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProjectPriority } from '@/shared/model/project-priority.model';
import ProjectPriorityService from './project-priority.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ProjectPriorityDetails extends Vue {
  @Inject('projectPriorityService') private projectPriorityService: () => ProjectPriorityService;
  @Inject('alertService') private alertService: () => AlertService;

  public projectPriority: IProjectPriority = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectPriorityId) {
        vm.retrieveProjectPriority(to.params.projectPriorityId);
      }
    });
  }

  public retrieveProjectPriority(projectPriorityId) {
    this.projectPriorityService()
      .find(projectPriorityId)
      .then(res => {
        this.projectPriority = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
