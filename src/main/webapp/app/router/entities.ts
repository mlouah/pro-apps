import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const MoM = () => import('@/entities/mo-m/mo-m.vue');
// prettier-ignore
const MoMUpdate = () => import('@/entities/mo-m/mo-m-update.vue');
// prettier-ignore
const MoMDetails = () => import('@/entities/mo-m/mo-m-details.vue');
// prettier-ignore
const Task = () => import('@/entities/task/task.vue');
// prettier-ignore
const TaskUpdate = () => import('@/entities/task/task-update.vue');
// prettier-ignore
const TaskDetails = () => import('@/entities/task/task-details.vue');
// prettier-ignore
const Person = () => import('@/entities/person/person.vue');
// prettier-ignore
const PersonUpdate = () => import('@/entities/person/person-update.vue');
// prettier-ignore
const PersonDetails = () => import('@/entities/person/person-details.vue');
// prettier-ignore
const Client = () => import('@/entities/client/client.vue');
// prettier-ignore
const ClientUpdate = () => import('@/entities/client/client-update.vue');
// prettier-ignore
const ClientDetails = () => import('@/entities/client/client-details.vue');
// prettier-ignore
const Project = () => import('@/entities/project/project.vue');
// prettier-ignore
const ProjectUpdate = () => import('@/entities/project/project-update.vue');
// prettier-ignore
const ProjectDetails = () => import('@/entities/project/project-details.vue');
// prettier-ignore
const TaskStatus = () => import('@/entities/task-status/task-status.vue');
// prettier-ignore
const TaskStatusUpdate = () => import('@/entities/task-status/task-status-update.vue');
// prettier-ignore
const TaskStatusDetails = () => import('@/entities/task-status/task-status-details.vue');
// prettier-ignore
const Company = () => import('@/entities/company/company.vue');
// prettier-ignore
const CompanyUpdate = () => import('@/entities/company/company-update.vue');
// prettier-ignore
const CompanyDetails = () => import('@/entities/company/company-details.vue');
// prettier-ignore
const ProjectStatus = () => import('@/entities/project-status/project-status.vue');
// prettier-ignore
const ProjectStatusUpdate = () => import('@/entities/project-status/project-status-update.vue');
// prettier-ignore
const ProjectStatusDetails = () => import('@/entities/project-status/project-status-details.vue');
// prettier-ignore
const ProjectStatusCode = () => import('@/entities/project-status-code/project-status-code.vue');
// prettier-ignore
const ProjectStatusCodeUpdate = () => import('@/entities/project-status-code/project-status-code-update.vue');
// prettier-ignore
const ProjectStatusCodeDetails = () => import('@/entities/project-status-code/project-status-code-details.vue');
// prettier-ignore
const ProjectPriority = () => import('@/entities/project-priority/project-priority.vue');
// prettier-ignore
const ProjectPriorityUpdate = () => import('@/entities/project-priority/project-priority-update.vue');
// prettier-ignore
const ProjectPriorityDetails = () => import('@/entities/project-priority/project-priority-details.vue');
// prettier-ignore
const ProjectCategory = () => import('@/entities/project-category/project-category.vue');
// prettier-ignore
const ProjectCategoryUpdate = () => import('@/entities/project-category/project-category-update.vue');
// prettier-ignore
const ProjectCategoryDetails = () => import('@/entities/project-category/project-category-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'mo-m',
      name: 'MoM',
      component: MoM,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'mo-m/new',
      name: 'MoMCreate',
      component: MoMUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'mo-m/:moMId/edit',
      name: 'MoMEdit',
      component: MoMUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'mo-m/:moMId/view',
      name: 'MoMView',
      component: MoMDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task',
      name: 'Task',
      component: Task,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task/new',
      name: 'TaskCreate',
      component: TaskUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task/:taskId/edit',
      name: 'TaskEdit',
      component: TaskUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task/:taskId/view',
      name: 'TaskView',
      component: TaskDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person',
      name: 'Person',
      component: Person,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/new',
      name: 'PersonCreate',
      component: PersonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/:personId/edit',
      name: 'PersonEdit',
      component: PersonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/:personId/view',
      name: 'PersonView',
      component: PersonDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client',
      name: 'Client',
      component: Client,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/new',
      name: 'ClientCreate',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/edit',
      name: 'ClientEdit',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/view',
      name: 'ClientView',
      component: ClientDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project',
      name: 'Project',
      component: Project,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/new',
      name: 'ProjectCreate',
      component: ProjectUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/:projectId/edit',
      name: 'ProjectEdit',
      component: ProjectUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/:projectId/view',
      name: 'ProjectView',
      component: ProjectDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task-status',
      name: 'TaskStatus',
      component: TaskStatus,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task-status/new',
      name: 'TaskStatusCreate',
      component: TaskStatusUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task-status/:taskStatusId/edit',
      name: 'TaskStatusEdit',
      component: TaskStatusUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task-status/:taskStatusId/view',
      name: 'TaskStatusView',
      component: TaskStatusDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'company',
      name: 'Company',
      component: Company,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'company/new',
      name: 'CompanyCreate',
      component: CompanyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'company/:companyId/edit',
      name: 'CompanyEdit',
      component: CompanyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'company/:companyId/view',
      name: 'CompanyView',
      component: CompanyDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-status',
      name: 'ProjectStatus',
      component: ProjectStatus,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-status/new',
      name: 'ProjectStatusCreate',
      component: ProjectStatusUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-status/:projectStatusId/edit',
      name: 'ProjectStatusEdit',
      component: ProjectStatusUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-status/:projectStatusId/view',
      name: 'ProjectStatusView',
      component: ProjectStatusDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-status-code',
      name: 'ProjectStatusCode',
      component: ProjectStatusCode,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-status-code/new',
      name: 'ProjectStatusCodeCreate',
      component: ProjectStatusCodeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-status-code/:projectStatusCodeId/edit',
      name: 'ProjectStatusCodeEdit',
      component: ProjectStatusCodeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-status-code/:projectStatusCodeId/view',
      name: 'ProjectStatusCodeView',
      component: ProjectStatusCodeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-priority',
      name: 'ProjectPriority',
      component: ProjectPriority,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-priority/new',
      name: 'ProjectPriorityCreate',
      component: ProjectPriorityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-priority/:projectPriorityId/edit',
      name: 'ProjectPriorityEdit',
      component: ProjectPriorityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-priority/:projectPriorityId/view',
      name: 'ProjectPriorityView',
      component: ProjectPriorityDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-category',
      name: 'ProjectCategory',
      component: ProjectCategory,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-category/new',
      name: 'ProjectCategoryCreate',
      component: ProjectCategoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-category/:projectCategoryId/edit',
      name: 'ProjectCategoryEdit',
      component: ProjectCategoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project-category/:projectCategoryId/view',
      name: 'ProjectCategoryView',
      component: ProjectCategoryDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
