<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectPriorityHeading">
      <span v-text="$t('proAppsApp.projectPriority.home.title')" id="project-priority-heading">Project Priorities</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.projectPriority.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ProjectPriorityCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project-priority"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.projectPriority.home.createLabel')"> Create a new Project Priority </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectPriorities && projectPriorities.length === 0">
      <span v-text="$t('proAppsApp.projectPriority.home.notFound')">No projectPriorities found</span>
    </div>
    <div class="table-responsive" v-if="projectPriorities && projectPriorities.length > 0">
      <table class="table table-striped" aria-describedby="projectPriorities">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectPriority.code')">Code</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectPriority.priority')">Priority</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectPriority.dateCreation')">Date Creation</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectPriority.dateModify')">Date Modify</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectPriority.lastModifyBy')">Last Modify By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectPriority.createdBy')">Created By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectPriority.notes')">Notes</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectPriority in projectPriorities" :key="projectPriority.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectPriorityView', params: { projectPriorityId: projectPriority.id } }">{{
                projectPriority.id
              }}</router-link>
            </td>
            <td>{{ projectPriority.code }}</td>
            <td>{{ projectPriority.priority }}</td>
            <td>{{ projectPriority.dateCreation ? $d(Date.parse(projectPriority.dateCreation), 'short') : '' }}</td>
            <td>{{ projectPriority.dateModify ? $d(Date.parse(projectPriority.dateModify), 'short') : '' }}</td>
            <td>{{ projectPriority.lastModifyBy }}</td>
            <td>{{ projectPriority.createdBy }}</td>
            <td>{{ projectPriority.notes }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProjectPriorityView', params: { projectPriorityId: projectPriority.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProjectPriorityEdit', params: { projectPriorityId: projectPriority.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectPriority)"
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
          id="proAppsApp.projectPriority.delete.question"
          data-cy="projectPriorityDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-projectPriority-heading" v-text="$t('proAppsApp.projectPriority.delete.question', { id: removeId })">
          Are you sure you want to delete this Project Priority?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-projectPriority"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeProjectPriority()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project-priority.component.ts"></script>
