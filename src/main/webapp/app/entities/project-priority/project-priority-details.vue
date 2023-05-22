<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectPriority">
        <h2 class="jh-entity-heading" data-cy="projectPriorityDetailsHeading">
          <span v-text="$t('proAppsApp.projectPriority.detail.title')">ProjectPriority</span> {{ projectPriority.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.projectPriority.code')">Code</span>
          </dt>
          <dd>
            <span>{{ projectPriority.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectPriority.priority')">Priority</span>
          </dt>
          <dd>
            <span>{{ projectPriority.priority }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectPriority.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="projectPriority.dateCreation">{{ $d(Date.parse(projectPriority.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectPriority.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="projectPriority.dateModify">{{ $d(Date.parse(projectPriority.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectPriority.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ projectPriority.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectPriority.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ projectPriority.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectPriority.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ projectPriority.notes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="projectPriority.id"
          :to="{ name: 'ProjectPriorityEdit', params: { projectPriorityId: projectPriority.id } }"
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

<script lang="ts" src="./project-priority-details.component.ts"></script>
