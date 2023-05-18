<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="project">
        <h2 class="jh-entity-heading" data-cy="projectDetailsHeading">
          <span v-text="$t('proAppsApp.project.detail.title')">Project</span> {{ project.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.project.name')">Name</span>
          </dt>
          <dd>
            <span>{{ project.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.code')">Code</span>
          </dt>
          <dd>
            <span>{{ project.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ project.notes }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.isInternal')">Is Internal</span>
          </dt>
          <dd>
            <span>{{ project.isInternal }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="project.dateCreation">{{ $d(Date.parse(project.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="project.dateModify">{{ $d(Date.parse(project.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ project.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ project.createdBy }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="project.id" :to="{ name: 'ProjectEdit', params: { projectId: project.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./project-details.component.ts"></script>
