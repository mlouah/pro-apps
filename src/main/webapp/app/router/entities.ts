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
const TaskPriority = () => import('@/entities/task-priority/task-priority.vue');
// prettier-ignore
const TaskPriorityUpdate = () => import('@/entities/task-priority/task-priority-update.vue');
// prettier-ignore
const TaskPriorityDetails = () => import('@/entities/task-priority/task-priority-details.vue');
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
      path: 'task-priority',
      name: 'TaskPriority',
      component: TaskPriority,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task-priority/new',
      name: 'TaskPriorityCreate',
      component: TaskPriorityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task-priority/:taskPriorityId/edit',
      name: 'TaskPriorityEdit',
      component: TaskPriorityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'task-priority/:taskPriorityId/view',
      name: 'TaskPriorityView',
      component: TaskPriorityDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
