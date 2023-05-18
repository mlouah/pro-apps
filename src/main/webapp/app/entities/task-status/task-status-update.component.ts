import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import TaskService from '@/entities/task/task.service';
import { ITask } from '@/shared/model/task.model';

import { ITaskStatus, TaskStatus } from '@/shared/model/task-status.model';
import TaskStatusService from './task-status.service';

const validations: any = {
  taskStatus: {
    code: {
      required,
    },
    status: {},
    statusPicto: {},
    notes: {},
  },
};

@Component({
  validations,
})
export default class TaskStatusUpdate extends mixins(JhiDataUtils) {
  @Inject('taskStatusService') private taskStatusService: () => TaskStatusService;
  @Inject('alertService') private alertService: () => AlertService;

  public taskStatus: ITaskStatus = new TaskStatus();

  @Inject('taskService') private taskService: () => TaskService;

  public tasks: ITask[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskStatusId) {
        vm.retrieveTaskStatus(to.params.taskStatusId);
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
    if (this.taskStatus.id) {
      this.taskStatusService()
        .update(this.taskStatus)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.taskStatus.updated', { param: param.id });
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
      this.taskStatusService()
        .create(this.taskStatus)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.taskStatus.created', { param: param.id });
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

  public retrieveTaskStatus(taskStatusId): void {
    this.taskStatusService()
      .find(taskStatusId)
      .then(res => {
        this.taskStatus = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public clearInputImage(field, fieldContentType, idInput): void {
    if (this.taskStatus && field && fieldContentType) {
      if (Object.prototype.hasOwnProperty.call(this.taskStatus, field)) {
        this.taskStatus[field] = null;
      }
      if (Object.prototype.hasOwnProperty.call(this.taskStatus, fieldContentType)) {
        this.taskStatus[fieldContentType] = null;
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
