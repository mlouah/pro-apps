<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="taskPriority">
        <h2 class="jh-entity-heading" data-cy="taskPriorityDetailsHeading">
          <span v-text="$t('proAppsApp.taskPriority.detail.title')">TaskPriority</span> {{ taskPriority.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.taskPriority.code')">Code</span>
          </dt>
          <dd>
            <span>{{ taskPriority.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskPriority.priority')">Priority</span>
          </dt>
          <dd>
            <span>{{ taskPriority.priority }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskPriority.taskPriorityPicto')">Task Priority Picto</span>
          </dt>
          <dd>
            <div v-if="taskPriority.taskPriorityPicto">
              <a v-on:click="openFile(taskPriority.taskPriorityPictoContentType, taskPriority.taskPriorityPicto)">
                <img
                  v-bind:src="'data:' + taskPriority.taskPriorityPictoContentType + ';base64,' + taskPriority.taskPriorityPicto"
                  style="max-width: 100%"
                  alt="taskPriority image"
                />
              </a>
              {{ taskPriority.taskPriorityPictoContentType }}, {{ byteSize(taskPriority.taskPriorityPicto) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.taskPriority.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ taskPriority.notes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="taskPriority.id"
          :to="{ name: 'TaskPriorityEdit', params: { taskPriorityId: taskPriority.id } }"
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

<script lang="ts" src="./task-priority-details.component.ts"></script>
