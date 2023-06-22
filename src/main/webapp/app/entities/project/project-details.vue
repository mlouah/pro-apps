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
            <span v-text="$t('proAppsApp.project.startDate')">Start Date</span>
          </dt>
          <dd>
            <span>{{ project.startDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.realEndtDate')">Real Endt Date</span>
          </dt>
          <dd>
            <span>{{ project.realEndtDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.projectDescription')">Project Description</span>
          </dt>
          <dd>
            <span v-html="project.projectDescription"></span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.initialEndDate')">Initial End Date</span>
          </dt>
          <dd>
            <span>{{ project.initialEndDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.initialCost')">Initial Cost</span>
          </dt>
          <dd>
            <span>{{ project.initialCost }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.initialWorkLoad')">Initial Work Load</span>
          </dt>
          <dd>
            <span>{{ project.initialWorkLoad }}</span>
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
          <dt>
            <span v-text="$t('proAppsApp.project.notes')">Notes</span>
          </dt>
          <dd>
            <span v-html="project.notes"></span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.projectPriority')">Project Priority</span>
          </dt>
          <dd>
            <div v-if="project.projectPriority">
              <router-link :to="{ name: 'ProjectPriorityView', params: { projectPriorityId: project.projectPriority.id } }">{{
                project.projectPriority.code
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.projectCategory')">Project Category</span>
          </dt>
          <dd>
            <div v-if="project.projectCategory">
              <router-link :to="{ name: 'ProjectCategoryView', params: { projectCategoryId: project.projectCategory.id } }">{{
                project.projectCategory.code
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.clientCode')">Client Code</span>
          </dt>
          <dd>
            <div v-if="project.clientCode">
              <router-link :to="{ name: 'ClientView', params: { clientId: project.clientCode.id } }">{{
                project.clientCode.code
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.internalProjectManager')">Internal Project Manager</span>
          </dt>
          <dd>
            <div v-if="project.internalProjectManager">
              <router-link :to="{ name: 'PersonView', params: { personId: project.internalProjectManager.id } }">{{
                project.internalProjectManager.name
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.project.company')">Company</span>
          </dt>
          <dd>
            <div v-if="project.company">
              <router-link :to="{ name: 'CompanyView', params: { companyId: project.company.id } }">{{ project.company.code }}</router-link>
            </div>
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
