<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectStatusCode">
        <h2 class="jh-entity-heading" data-cy="projectStatusCodeDetailsHeading">
          <span v-text="$t('proAppsApp.projectStatusCode.detail.title')">ProjectStatusCode</span> {{ projectStatusCode.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.projectStatusCode.code')">Code</span>
          </dt>
          <dd>
            <span>{{ projectStatusCode.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatusCode.status')">Status</span>
          </dt>
          <dd>
            <span>{{ projectStatusCode.status }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatusCode.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="projectStatusCode.dateCreation">{{ $d(Date.parse(projectStatusCode.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatusCode.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="projectStatusCode.dateModify">{{ $d(Date.parse(projectStatusCode.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatusCode.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ projectStatusCode.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatusCode.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ projectStatusCode.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatusCode.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ projectStatusCode.notes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="projectStatusCode.id"
          :to="{ name: 'ProjectStatusCodeEdit', params: { projectStatusCodeId: projectStatusCode.id } }"
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

<script lang="ts" src="./project-status-code-details.component.ts"></script>
