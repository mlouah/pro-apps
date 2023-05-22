<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectCategoryHeading">
      <span v-text="$t('proAppsApp.projectCategory.home.title')" id="project-category-heading">Project Categories</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.projectCategory.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ProjectCategoryCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project-category"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.projectCategory.home.createLabel')"> Create a new Project Category </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectCategories && projectCategories.length === 0">
      <span v-text="$t('proAppsApp.projectCategory.home.notFound')">No projectCategories found</span>
    </div>
    <div class="table-responsive" v-if="projectCategories && projectCategories.length > 0">
      <table class="table table-striped" aria-describedby="projectCategories">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectCategory.code')">Code</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectCategory.category')">Category</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectCategory.dateCreation')">Date Creation</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectCategory.dateModify')">Date Modify</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectCategory.lastModifyBy')">Last Modify By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectCategory.createdBy')">Created By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectCategory.notes')">Notes</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectCategory in projectCategories" :key="projectCategory.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectCategoryView', params: { projectCategoryId: projectCategory.id } }">{{
                projectCategory.id
              }}</router-link>
            </td>
            <td>{{ projectCategory.code }}</td>
            <td>{{ projectCategory.category }}</td>
            <td>{{ projectCategory.dateCreation ? $d(Date.parse(projectCategory.dateCreation), 'short') : '' }}</td>
            <td>{{ projectCategory.dateModify ? $d(Date.parse(projectCategory.dateModify), 'short') : '' }}</td>
            <td>{{ projectCategory.lastModifyBy }}</td>
            <td>{{ projectCategory.createdBy }}</td>
            <td>{{ projectCategory.notes }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProjectCategoryView', params: { projectCategoryId: projectCategory.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProjectCategoryEdit', params: { projectCategoryId: projectCategory.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectCategory)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="proAppsApp.projectCategory.delete.question"
          data-cy="projectCategoryDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-projectCategory-heading" v-text="$t('proAppsApp.projectCategory.delete.question', { id: removeId })">
          Are you sure you want to delete this Project Category?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-projectCategory"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeProjectCategory()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project-category.component.ts"></script>
