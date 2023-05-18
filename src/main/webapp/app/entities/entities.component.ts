import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import MoMService from './mo-m/mo-m.service';
import TaskService from './task/task.service';
import PersonService from './person/person.service';
import ProjectService from './project/project.service';
import TaskStatusService from './task-status/task-status.service';
import TaskPriorityService from './task-priority/task-priority.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('moMService') private moMService = () => new MoMService();
  @Provide('taskService') private taskService = () => new TaskService();
  @Provide('personService') private personService = () => new PersonService();
  @Provide('projectService') private projectService = () => new ProjectService();
  @Provide('taskStatusService') private taskStatusService = () => new TaskStatusService();
  @Provide('taskPriorityService') private taskPriorityService = () => new TaskPriorityService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
