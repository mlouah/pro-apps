import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import TaskService from '@/entities/task/task.service';
import { ITask } from '@/shared/model/task.model';

import { ITaskPriority, TaskPriority } from '@/shared/model/task-priority.model';
import TaskPriorityService from './task-priority.service';

const validations: any = {
  taskPriority: {
    code: {
      required,
    },
    priority: {},
    taskPriorityPicto: {},
    notes: {},
  },
};

@Component({
  validations,
})
export default class TaskPriorityUpdate extends mixins(JhiDataUtils) {
  @Inject('taskPriorityService') private taskPriorityService: () => TaskPriorityService;
  @Inject('alertService') private alertService: () => AlertService;

  public taskPriority: ITaskPriority = new TaskPriority();

  @Inject('taskService') private taskService: () => TaskService;

  public tasks: ITask[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskPriorityId) {
        vm.retrieveTaskPriority(to.params.taskPriorityId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.taskPriority.id) {
      this.taskPriorityService()
        .update(this.taskPriority)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.taskPriority.updated', { param: param.id });
          return (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.taskPriorityService()
        .create(this.taskPriority)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.taskPriority.created', { param: param.id });
          (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveTaskPriority(taskPriorityId): void {
    this.taskPriorityService()
      .find(taskPriorityId)
      .then(res => {
        this.taskPriority = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public clearInputImage(field, fieldContentType, idInput): void {
    if (this.taskPriority && field && fieldContentType) {
      if (Object.prototype.hasOwnProperty.call(this.taskPriority, field)) {
        this.taskPriority[field] = null;
      }
      if (Object.prototype.hasOwnProperty.call(this.taskPriority, fieldContentType)) {
        this.taskPriority[fieldContentType] = null;
      }
      if (idInput) {
        (<any>this).$refs[idInput] = null;
      }
    }
  }

  public initRelationships(): void {
    this.taskService()
      .retrieve()
      .then(res => {
        this.tasks = res.data;
      });
  }
}
