import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import ProjectStatusService from '@/entities/project-status/project-status.service';
import { IProjectStatus } from '@/shared/model/project-status.model';

import { IProjectStatusCode, ProjectStatusCode } from '@/shared/model/project-status-code.model';
import ProjectStatusCodeService from './project-status-code.service';

const validations: any = {
  projectStatusCode: {
    code: {
      required,
    },
    status: {},
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
export default class ProjectStatusCodeUpdate extends Vue {
  @Inject('projectStatusCodeService') private projectStatusCodeService: () => ProjectStatusCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public projectStatusCode: IProjectStatusCode = new ProjectStatusCode();

  @Inject('projectStatusService') private projectStatusService: () => ProjectStatusService;

  public projectStatuses: IProjectStatus[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectStatusCodeId) {
        vm.retrieveProjectStatusCode(to.params.projectStatusCodeId);
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
    if (this.projectStatusCode.id) {
      this.projectStatusCodeService()
        .update(this.projectStatusCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.projectStatusCode.updated', { param: param.id });
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
      this.projectStatusCodeService()
        .create(this.projectStatusCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.projectStatusCode.created', { param: param.id });
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
      this.projectStatusCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.projectStatusCode[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.projectStatusCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.projectStatusCode[field] = null;
    }
  }

  public retrieveProjectStatusCode(projectStatusCodeId): void {
    this.projectStatusCodeService()
      .find(projectStatusCodeId)
      .then(res => {
        res.dateCreation = new Date(res.dateCreation);
        res.dateModify = new Date(res.dateModify);
        this.projectStatusCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.projectStatusService()
      .retrieve()
      .then(res => {
        this.projectStatuses = res.data;
      });
  }
}
