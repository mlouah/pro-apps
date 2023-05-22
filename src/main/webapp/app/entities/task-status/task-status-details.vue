<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="taskStatus">
        <h2 class="jh-entity-heading" data-cy="taskStatusDetailsHeading">
          <span v-text="$t('proAppsApp.taskStatus.detail.title')">TaskStatus</span> {{ taskStatus.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.taskStatus.code')">Code</span>
          </dt>
          <dd>
            <span>{{ taskStatus.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskStatus.status')">Status</span>
          </dt>
          <dd>
            <span>{{ taskStatus.status }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskStatus.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="taskStatus.dateCreation">{{ $d(Date.parse(taskStatus.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskStatus.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="taskStatus.dateModify">{{ $d(Date.parse(taskStatus.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskStatus.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ taskStatus.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskStatus.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ taskStatus.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskStatus.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ taskStatus.notes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="taskStatus.id"
          :to="{ name: 'TaskStatusEdit', params: { taskStatusId: taskStatus.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-status-details.component.ts"></script>
