import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TaskStatusService from '@/entities/task-status/task-status.service';
import { ITaskStatus } from '@/shared/model/task-status.model';

import MoMService from '@/entities/mo-m/mo-m.service';
import { IMoM } from '@/shared/model/mo-m.model';

import ProjectService from '@/entities/project/project.service';
import { IProject } from '@/shared/model/project.model';

import PersonService from '@/entities/person/person.service';
import { IPerson } from '@/shared/model/person.model';

import { ITask, Task } from '@/shared/model/task.model';
import TaskService from './task.service';

const validations: any = {
  task: {
    subject: {},
    taskTitle: {
      required,
    },
    dealLine: {},
    isUrgent: {},
    dateCreation: {},
    dateModify: {},
    lastModifyBy: {},
    createdBy: {},
    notes: {},
  },
};

@Component({
  validations,
})
export default class TaskUpdate extends mixins(JhiDataUtils) {
  @Inject('taskService') private taskService: () => TaskService;
  @Inject('alertService') private alertService: () => AlertService;

  public task: ITask = new Task();

  @Inject('taskStatusService') private taskStatusService: () => TaskStatusService;

  public taskStatuses: ITaskStatus[] = [];

  @Inject('moMService') private moMService: () => MoMService;

  public moMS: IMoM[] = [];

  @Inject('projectService') private projectService: () => ProjectService;

  public projects: IProject[] = [];

  @Inject('personService') private personService: () => PersonService;

  public people: IPerson[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskId) {
        vm.retrieveTask(to.params.taskId);
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
    if (this.task.id) {
      this.taskService()
        .update(this.task)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.task.updated', { param: param.id });
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
      this.taskService()
        .create(this.task)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.task.created', { param: param.id });
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.task[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.task[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.task[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.task[field] = null;
    }
  }

  public retrieveTask(taskId): void {
    this.taskService()
      .find(taskId)
      .then(res => {
        res.dateCreation = new Date(res.dateCreation);
        res.dateModify = new Date(res.dateModify);
        this.task = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.taskStatusService()
      .retrieve()
      .then(res => {
        this.taskStatuses = res.data;
      });
    this.moMService()
      .retrieve()
      .then(res => {
        this.moMS = res.data;
      });
    this.projectService()
      .retrieve()
      .then(res => {
        this.projects = res.data;
      });
    this.personService()
      .retrieve()
      .then(res => {
        this.people = res.data;
      });
  }
}
