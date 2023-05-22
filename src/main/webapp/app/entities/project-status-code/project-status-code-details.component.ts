import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProjectStatusCode } from '@/shared/model/project-status-code.model';
import ProjectStatusCodeService from './project-status-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ProjectStatusCodeDetails extends Vue {
  @Inject('projectStatusCodeService') private projectStatusCodeService: () => ProjectStatusCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public projectStatusCode: IProjectStatusCode = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectStatusCodeId) {
        vm.retrieveProjectStatusCode(to.params.projectStatusCodeId);
      }
    });
  }

  public retrieveProjectStatusCode(projectStatusCodeId) {
    this.projectStatusCodeService()
      .find(projectStatusCodeId)
      .then(res => {
        this.projectStatusCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
