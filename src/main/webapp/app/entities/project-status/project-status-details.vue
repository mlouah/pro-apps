<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectStatus">
        <h2 class="jh-entity-heading" data-cy="projectStatusDetailsHeading">
          <span v-text="$t('proAppsApp.projectStatus.detail.title')">ProjectStatus</span> {{ projectStatus.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.statusDate')">Status Date</span>
          </dt>
          <dd>
            <span>{{ projectStatus.statusDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.statusTitle')">Status Title</span>
          </dt>
          <dd>
            <span>{{ projectStatus.statusTitle }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.updatedProgress')">Updated Progress</span>
          </dt>
          <dd>
            <span>{{ projectStatus.updatedProgress }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.updatedWorkloadEAC')">Updated Workload EAC</span>
          </dt>
          <dd>
            <span>{{ projectStatus.updatedWorkloadEAC }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.updatedEndDate')">Updated End Date</span>
          </dt>
          <dd>
            <span>{{ projectStatus.updatedEndDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.updatedCost')">Updated Cost</span>
          </dt>
          <dd>
            <span>{{ projectStatus.updatedCost }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="projectStatus.dateCreation">{{ $d(Date.parse(projectStatus.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="projectStatus.dateModify">{{ $d(Date.parse(projectStatus.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ projectStatus.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ projectStatus.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ projectStatus.notes }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.projectName')">Project Name</span>
          </dt>
          <dd>
            <div v-if="projectStatus.projectName">
              <router-link :to="{ name: 'ProjectView', params: { projectId: projectStatus.projectName.id } }">{{
                projectStatus.projectName.code
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectStatus.projectStatusCode')">Project Status Code</span>
          </dt>
          <dd>
            <div v-if="projectStatus.projectStatusCode">
              <router-link :to="{ name: 'ProjectStatusCodeView', params: { projectStatusCodeId: projectStatus.projectStatusCode.id } }">{{
                projectStatus.projectStatusCode.code
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="projectStatus.id"
          :to="{ name: 'ProjectStatusEdit', params: { projectStatusId: projectStatus.id } }"
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

<script lang="ts" src="./project-status-details.component.ts"></script>
