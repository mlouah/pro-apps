<template>
  <div>
    <h2 id="page-heading" data-cy="TaskStatusHeading">
      <span v-text="$t('proAppsApp.taskStatus.home.title')" id="task-status-heading">Task Statuses</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.taskStatus.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TaskStatusCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-task-status"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.taskStatus.home.createLabel')"> Create a new Task Status </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && taskStatuses && taskStatuses.length === 0">
      <span v-text="$t('proAppsApp.taskStatus.home.notFound')">No taskStatuses found</span>
    </div>
    <div class="table-responsive" v-if="taskStatuses && taskStatuses.length > 0">
      <table class="table table-striped" aria-describedby="taskStatuses">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskStatus.code')">Code</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskStatus.status')">Status</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskStatus.dateCreation')">Date Creation</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskStatus.dateModify')">Date Modify</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskStatus.lastModifyBy')">Last Modify By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskStatus.createdBy')">Created By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskStatus.notes')">Notes</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="taskStatus in taskStatuses" :key="taskStatus.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TaskStatusView', params: { taskStatusId: taskStatus.id } }">{{ taskStatus.id }}</router-link>
            </td>
            <td>{{ taskStatus.code }}</td>
            <td>{{ taskStatus.status }}</td>
            <td>{{ taskStatus.dateCreation ? $d(Date.parse(taskStatus.dateCreation), 'short') : '' }}</td>
            <td>{{ taskStatus.dateModify ? $d(Date.parse(taskStatus.dateModify), 'short') : '' }}</td>
            <td>{{ taskStatus.lastModifyBy }}</td>
            <td>{{ taskStatus.createdBy }}</td>
            <td>{{ taskStatus.notes }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TaskStatusView', params: { taskStatusId: taskStatus.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TaskStatusEdit', params: { taskStatusId: taskStatus.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(taskStatus)"
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
        ><span id="proAppsApp.taskStatus.delete.question" data-cy="taskStatusDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-taskStatus-heading" v-text="$t('proAppsApp.taskStatus.delete.question', { id: removeId })">
          Are you sure you want to delete this Task Status?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-taskStatus"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTaskStatus()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./task-status.component.ts"></script>
