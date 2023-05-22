<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectStatusCodeHeading">
      <span v-text="$t('proAppsApp.projectStatusCode.home.title')" id="project-status-code-heading">Project Status Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.projectStatusCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ProjectStatusCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-project-status-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.projectStatusCode.home.createLabel')"> Create a new Project Status Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectStatusCodes && projectStatusCodes.length === 0">
      <span v-text="$t('proAppsApp.projectStatusCode.home.notFound')">No projectStatusCodes found</span>
    </div>
    <div class="table-responsive" v-if="projectStatusCodes && projectStatusCodes.length > 0">
      <table class="table table-striped" aria-describedby="projectStatusCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectStatusCode.code')">Code</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectStatusCode.status')">Status</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectStatusCode.dateCreation')">Date Creation</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectStatusCode.dateModify')">Date Modify</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectStatusCode.lastModifyBy')">Last Modify By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectStatusCode.createdBy')">Created By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.projectStatusCode.notes')">Notes</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectStatusCode in projectStatusCodes" :key="projectStatusCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectStatusCodeView', params: { projectStatusCodeId: projectStatusCode.id } }">{{
                projectStatusCode.id
              }}</router-link>
            </td>
            <td>{{ projectStatusCode.code }}</td>
            <td>{{ projectStatusCode.status }}</td>
            <td>{{ projectStatusCode.dateCreation ? $d(Date.parse(projectStatusCode.dateCreation), 'short') : '' }}</td>
            <td>{{ projectStatusCode.dateModify ? $d(Date.parse(projectStatusCode.dateModify), 'short') : '' }}</td>
            <td>{{ projectStatusCode.lastModifyBy }}</td>
            <td>{{ projectStatusCode.createdBy }}</td>
            <td>{{ projectStatusCode.notes }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProjectStatusCodeView', params: { projectStatusCodeId: projectStatusCode.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProjectStatusCodeEdit', params: { projectStatusCodeId: projectStatusCode.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectStatusCode)"
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
          id="proAppsApp.projectStatusCode.delete.question"
          data-cy="projectStatusCodeDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-projectStatusCode-heading" v-text="$t('proAppsApp.projectStatusCode.delete.question', { id: removeId })">
          Are you sure you want to delete this Project Status Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-projectStatusCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeProjectStatusCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./project-status-code.component.ts"></script>
