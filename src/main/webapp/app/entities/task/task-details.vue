<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="task">
        <h2 class="jh-entity-heading" data-cy="taskDetailsHeading">
          <span v-text="$t('proAppsApp.task.detail.title')">Task</span> {{ task.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.task.subject')">Subject</span>
          </dt>
          <dd>
            <span>{{ task.subject }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.taskTitle')">Task Title</span>
          </dt>
          <dd>
            <span>{{ task.taskTitle }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.dealLine')">Deal Line</span>
          </dt>
          <dd>
            <span>{{ task.dealLine }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.isUrgent')">Is Urgent</span>
          </dt>
          <dd>
            <span>{{ task.isUrgent }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="task.dateCreation">{{ $d(Date.parse(task.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="task.dateModify">{{ $d(Date.parse(task.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ task.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ task.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ task.notes }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.taskStatus')">Task Status</span>
          </dt>
          <dd>
            <div v-if="task.taskStatus">
              <router-link :to="{ name: 'TaskStatusView', params: { taskStatusId: task.taskStatus.id } }">{{
                task.taskStatus.code
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.taskMoM')">Task Mo M</span>
          </dt>
          <dd>
            <div v-if="task.taskMoM">
              <router-link :to="{ name: 'MoMView', params: { moMId: task.taskMoM.id } }">{{ task.taskMoM.title }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.projectName')">Project Name</span>
          </dt>
          <dd>
            <div v-if="task.projectName">
              <router-link :to="{ name: 'ProjectView', params: { projectId: task.projectName.id } }">{{
                task.projectName.code
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.task.taskOwner')">Task Owner</span>
          </dt>
          <dd>
            <div v-if="task.taskOwner">
              <router-link :to="{ name: 'PersonView', params: { personId: task.taskOwner.id } }">{{ task.taskOwner.name }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="task.id" :to="{ name: 'TaskEdit', params: { taskId: task.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-details.component.ts"></script>
