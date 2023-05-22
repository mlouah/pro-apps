<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectCategory">
        <h2 class="jh-entity-heading" data-cy="projectCategoryDetailsHeading">
          <span v-text="$t('proAppsApp.projectCategory.detail.title')">ProjectCategory</span> {{ projectCategory.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.projectCategory.code')">Code</span>
          </dt>
          <dd>
            <span>{{ projectCategory.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectCategory.category')">Category</span>
          </dt>
          <dd>
            <span>{{ projectCategory.category }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectCategory.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="projectCategory.dateCreation">{{ $d(Date.parse(projectCategory.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectCategory.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="projectCategory.dateModify">{{ $d(Date.parse(projectCategory.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectCategory.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ projectCategory.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectCategory.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ projectCategory.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.projectCategory.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ projectCategory.notes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="projectCategory.id"
          :to="{ name: 'ProjectCategoryEdit', params: { projectCategoryId: projectCategory.id } }"
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

<script lang="ts" src="./project-category-details.component.ts"></script>
