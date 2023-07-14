import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import MoMService from '@/entities/mo-m/mo-m.service';
import { IMoM } from '@/shared/model/mo-m.model';

import TaskService from '@/entities/task/task.service';
import { ITask } from '@/shared/model/task.model';

import ProjectPriorityService from '@/entities/project-priority/project-priority.service';
import { IProjectPriority } from '@/shared/model/project-priority.model';

import ProjectCategoryService from '@/entities/project-category/project-category.service';
import { IProjectCategory } from '@/shared/model/project-category.model';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import PersonService from '@/entities/person/person.service';
import { IPerson } from '@/shared/model/person.model';

import CompanyService from '@/entities/company/company.service';
import { ICompany } from '@/shared/model/company.model';

import TaskStatusService from '@/entities/task-status/task-status.service';
import { ITaskStatus } from '@/shared/model/task-status.model';

import { IProject, Project } from '@/shared/model/project.model';
import ProjectService from './project.service';

const validations: any = {
  project: {
    name: {
      required,
    },
    code: {
      required,
    },
    startDate: {},
    realEndtDate: {},
    projectDescription: {},
    initialEndDate: {},
    initialCost: {},
    initialWorkLoad: {},
    dateCreation: {},
    dateModify: {},
    lastModifyBy: {},
    createdBy: {},
    notes: {},
    objectives: {},
    todo: {},
    progress: {},
    projectStatus: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class ProjectUpdate extends mixins(JhiDataUtils) {
  @Inject('projectService') private projectService: () => ProjectService;
  @Inject('alertService') private alertService: () => AlertService;

  public project: IProject = new Project();

  @Inject('moMService') private moMService: () => MoMService;

  public moMS: IMoM[] = [];

  @Inject('taskService') private taskService: () => TaskService;

  public tasks: ITask[] = [];

  @Inject('projectPriorityService') private projectPriorityService: () => ProjectPriorityService;

  public projectPriorities: IProjectPriority[] = [];

  @Inject('projectCategoryService') private projectCategoryService: () => ProjectCategoryService;

  public projectCategories: IProjectCategory[] = [];

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];

  @Inject('personService') private personService: () => PersonService;

  public people: IPerson[] = [];

  @Inject('companyService') private companyService: () => CompanyService;

  public companies: ICompany[] = [];

  @Inject('taskStatusService') private taskStatusService: () => TaskStatusService;

  public taskStatuses: ITaskStatus[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.projectId) {
        vm.retrieveProject(to.params.projectId);
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
    if (this.project.id) {
      this.projectService()
        .update(this.project)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.project.updated', { param: param.id });
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
      this.projectService()
        .create(this.project)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('proAppsApp.project.created', { param: param.id });
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
      this.project[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.project[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.project[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.project[field] = null;
    }
  }

  public retrieveProject(projectId): void {
    this.projectService()
      .find(projectId)
      .then(res => {
        res.dateCreation = new Date(res.dateCreation);
        res.dateModify = new Date(res.dateModify);
        this.project = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.moMService()
      .retrieve()
      .then(res => {
        this.moMS = res.data;
      });
    this.taskService()
      .retrieve()
      .then(res => {
        this.tasks = res.data;
      });
    this.projectPriorityService()
      .retrieve()
      .then(res => {
        this.projectPriorities = res.data;
      });
    this.projectCategoryService()
      .retrieve()
      .then(res => {
        this.projectCategories = res.data;
      });
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
    this.personService()
      .retrieve()
      .then(res => {
        this.people = res.data;
      });
    this.companyService()
      .retrieve()
      .then(res => {
        this.companies = res.data;
      });
    this.taskStatusService()
      .retrieve()
      .then(res => {
        this.taskStatuses = res.data;
      });
  }
}
