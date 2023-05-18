import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { ITaskPriority } from '@/shared/model/task-priority.model';
import TaskPriorityService from './task-priority.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TaskPriorityDetails extends mixins(JhiDataUtils) {
  @Inject('taskPriorityService') private taskPriorityService: () => TaskPriorityService;
  @Inject('alertService') private alertService: () => AlertService;

  public taskPriority: ITaskPriority = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskPriorityId) {
        vm.retrieveTaskPriority(to.params.taskPriorityId);
      }
    });
  }

  public retrieveTaskPriority(taskPriorityId) {
    this.taskPriorityService()
      .find(taskPriorityId)
      .then(res => {
        this.taskPriority = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
