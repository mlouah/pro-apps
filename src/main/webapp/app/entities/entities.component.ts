import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import MoMService from './mo-m/mo-m.service';
import TaskService from './task/task.service';
import PersonService from './person/person.service';
import ClientService from './client/client.service';
import ProjectService from './project/project.service';
import TaskStatusService from './task-status/task-status.service';
import CompanyService from './company/company.service';
import ProjectStatusService from './project-status/project-status.service';
import ProjectStatusCodeService from './project-status-code/project-status-code.service';
import ProjectPriorityService from './project-priority/project-priority.service';
import ProjectCategoryService from './project-category/project-category.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('moMService') private moMService = () => new MoMService();
  @Provide('taskService') private taskService = () => new TaskService();
  @Provide('personService') private personService = () => new PersonService();
  @Provide('clientService') private clientService = () => new ClientService();
  @Provide('projectService') private projectService = () => new ProjectService();
  @Provide('taskStatusService') private taskStatusService = () => new TaskStatusService();
  @Provide('companyService') private companyService = () => new CompanyService();
  @Provide('projectStatusService') private projectStatusService = () => new ProjectStatusService();
  @Provide('projectStatusCodeService') private projectStatusCodeService = () => new ProjectStatusCodeService();
  @Provide('projectPriorityService') private projectPriorityService = () => new ProjectPriorityService();
  @Provide('projectCategoryService') private projectCategoryService = () => new ProjectCategoryService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
