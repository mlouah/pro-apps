import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TaskService from '@/entities/task/task.service';
import { ITask } from '@/shared/model/task.model';

import ProjectService from '@/entities/project/project.service';
import { IProject } from '@/shared/model/project.model';

import { IMoM, MoM } from '@/shared/model/mo-m.model';
import MoMService from './mo-m.service';

const validations: any = {
  moM: {
    title: {
      required,
    },
    meetingDate: {},
    minutesWritingDate: {},
    meetingObjectif: {},
    meetingDocReferences: {},
    isMoMActionsClosed: {},
    subjectDecisions: {},
    meetingPhotoCapture: {},
    dateCreation: {},
    dateModify: {},
    lastModifyBy: {},
    createdBy: {},
    attendees: {},
    notes: {},
  },
};

@Component({
  validations,
})
export default class MoMUpdate extends mixins(JhiDataUtils) {
  @Inject('moMService') private moMService: () => MoMService;
  @Inject('alertService') private alertService: () => AlertService;

  public moM: IMoM = new MoM();

  @Inject('taskService') private taskService: () => TaskService;

  public tasks: ITask[] = [];

  @Inject('projectService') private projectService: () => ProjectService;

  public projects: IProject[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.moMId) {
        vm.retrieveMoM(to.params.moMId);
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
    if (this.moM.id) {
      this.moMService()
        .update(this.moM)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.moM.updated', { param: param.id });
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
      this.moMService()
        .create(this.moM)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.moM.created', { param: param.id });
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
      this.moM[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.moM[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.moM[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.moM[field] = null;
    }
  }

  public retrieveMoM(moMId): void {
    this.moMService()
      .find(moMId)
      .then(res => {
        res.dateCreation = new Date(res.dateCreation);
        res.dateModify = new Date(res.dateModify);
        this.moM = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public clearInputImage(field, fieldContentType, idInput): void {
    if (this.moM && field && fieldContentType) {
      if (Object.prototype.hasOwnProperty.call(this.moM, field)) {
        this.moM[field] = null;
      }
      if (Object.prototype.hasOwnProperty.call(this.moM, fieldContentType)) {
        this.moM[fieldContentType] = null;
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
    this.projectService()
      .retrieve()
      .then(res => {
        this.projects = res.data;
      });
  }
}
