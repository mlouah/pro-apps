<template>
  <div>
    <h2 id="page-heading" data-cy="TaskPriorityHeading">
      <span v-text="$t('proAppsApp.taskPriority.home.title')" id="task-priority-heading">Task Priorities</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.taskPriority.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TaskPriorityCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-task-priority"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.taskPriority.home.createLabel')"> Create a new Task Priority </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && taskPriorities && taskPriorities.length === 0">
      <span v-text="$t('proAppsApp.taskPriority.home.notFound')">No taskPriorities found</span>
    </div>
    <div class="table-responsive" v-if="taskPriorities && taskPriorities.length > 0">
      <table class="table table-striped" aria-describedby="taskPriorities">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskPriority.code')">Code</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskPriority.priority')">Priority</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskPriority.taskPriorityPicto')">Task Priority Picto</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.taskPriority.notes')">Notes</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="taskPriority in taskPriorities" :key="taskPriority.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TaskPriorityView', params: { taskPriorityId: taskPriority.id } }">{{
                taskPriority.id
              }}</router-link>
            </td>
            <td>{{ taskPriority.code }}</td>
            <td>{{ taskPriority.priority }}</td>
            <td>
              <a
                v-if="taskPriority.taskPriorityPicto"
                v-on:click="openFile(taskPriority.taskPriorityPictoContentType, taskPriority.taskPriorityPicto)"
              >
                <img
                  v-bind:src="'data:' + taskPriority.taskPriorityPictoContentType + ';base64,' + taskPriority.taskPriorityPicto"
                  style="max-height: 30px"
                  alt="taskPriority image"
                />
              </a>
              <span v-if="taskPriority.taskPriorityPicto"
                >{{ taskPriority.taskPriorityPictoContentType }}, {{ byteSize(taskPriority.taskPriorityPicto) }}</span
              >
            </td>
            <td>{{ taskPriority.notes }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TaskPriorityView', params: { taskPriorityId: taskPriority.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TaskPriorityEdit', params: { taskPriorityId: taskPriority.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(taskPriority)"
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
        ><span id="proAppsApp.taskPriority.delete.question" data-cy="taskPriorityDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-taskPriority-heading" v-text="$t('proAppsApp.taskPriority.delete.question', { id: removeId })">
          Are you sure you want to delete this Task Priority?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-taskPriority"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTaskPriority()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./task-priority.component.ts"></script>
