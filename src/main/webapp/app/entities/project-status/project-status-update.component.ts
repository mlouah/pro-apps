import { Component, Vue, Inject } from 'vue-property-decorator';

import { decimal, minValue, maxValue } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import { IProject } from '@/shared/model/project.model';

import ProjectStatusCodeService from '@/entities/project-status-code/project-status-code.service';
import { IProjectStatusCode } from '@/shared/model/project-status-code.model';

import { IProjectStatus, ProjectStatus } from '@/shared/model/project-status.model';
import ProjectStatusService from './project-status.service';

const validations: any = {
  projectStatus: {
    statusDate: {},
    statusTitle: {},
    updatedProgress: {
      decimal,
      min: minValue(0),
      max: maxValue(100),
    },
    updatedWorkloadEAC: {},
    updatedEndDate: {},
    updatedCost: {},
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
export default class ProjectStatusUpdate extends Vue {
  @Inject('projectStatusService') private projectStatusService: () => ProjectStatusService;
  @Inject('alertService') private alertService: () => AlertService;

  public projectStatus: IProjectStatus = new ProjectStatus();

  @Inject('projectService') private projectService: () => ProjectService;

  public projects: IProject[] = [];

  @Inject('projectStatusCodeService') private projectStatusCodeService: () => ProjectStatusCodeService;

  public projectStatusCodes: IProjectStatusCode[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectStatusId) {
        vm.retrieveProjectStatus(to.params.projectStatusId);
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
    if (this.projectStatus.id) {
      this.projectStatusService()
        .update(this.projectStatus)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.projectStatus.updated', { param: param.id });
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
      this.projectStatusService()
        .create(this.projectStatus)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.projectStatus.created', { param: param.id });
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
      this.projectStatus[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.projectStatus[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.projectStatus[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.projectStatus[field] = null;
    }
  }

  public retrieveProjectStatus(projectStatusId): void {
    this.projectStatusService()
      .find(projectStatusId)
      .then(res => {
        res.dateCreation = new Date(res.dateCreation);
        res.dateModify = new Date(res.dateModify);
        this.projectStatus = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.projectService()
      .retrieve()
      .then(res => {
        this.projects = res.data;
      });
    this.projectStatusCodeService()
      .retrieve()
      .then(res => {
        this.projectStatusCodes = res.data;
      });
  }
}
