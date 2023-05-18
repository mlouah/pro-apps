import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { ITaskStatus } from '@/shared/model/task-status.model';
import TaskStatusService from './task-status.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TaskStatusDetails extends mixins(JhiDataUtils) {
  @Inject('taskStatusService') private taskStatusService: () => TaskStatusService;
  @Inject('alertService') private alertService: () => AlertService;

  public taskStatus: ITaskStatus = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskStatusId) {
        vm.retrieveTaskStatus(to.params.taskStatusId);
      }
    });
  }

  public retrieveTaskStatus(taskStatusId) {
    this.taskStatusService()
      .find(taskStatusId)
      .then(res => {
        this.taskStatus = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
