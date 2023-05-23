<template>
  <div>
    <h2 id="page-heading" data-cy="TaskHeading">
      <span>{{ tasks.length }}</span>
      <span v-text="$t('proAppsApp.task.home.title')" id="task-heading"> Tasks </span>

      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.task.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="{ name: 'TaskCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-task">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.task.home.createLabel')"> Create a new Task </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tasks && tasks.length === 0">
      <span v-text="$t('proAppsApp.task.home.notFound')">No tasks found</span>
    </div>
    <div class="table-responsive" v-if="tasks && tasks.length > 0">
      <table class="table table-striped" aria-describedby="tasks">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>

            <th scope="row"><span v-text="$t('proAppsApp.task.subject')">Subject</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.taskTitle')">Task Title</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.dealLine')">Deal Line</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.isUrgent')">Is Urgent</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.dateCreation')">Date Creation</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.dateModify')">Date Modify</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.lastModifyBy')">Last Modify By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.createdBy')">Created By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.notes')">Notes</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.taskStatus')">Task Status</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.taskMoM')">Task Mo M</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.projectName')">Project Name</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.task.taskOwner')">Task Owner</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="task in tasks" :key="task.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TaskView', params: { taskId: task.id } }">{{ task.id }}</router-link>
            </td>

            <td>{{ task.subject }}</td>
            <td>{{ task.taskTitle }}</td>
            <td>
              <span v-if="isSameDay(task.dealLine)" class="text-danger">(*)</span>
              {{ task.dealLine }}
            </td>

            <td>
              <span v-if="task.isUrgent"> <font-awesome-icon icon="flag"></font-awesome-icon></span>
            </td>

            <td>{{ task.dateCreation ? $d(Date.parse(task.dateCreation), 'short') : '' }}</td>
            <td>{{ task.dateModify ? $d(Date.parse(task.dateModify), 'short') : '' }}</td>
            <td>{{ task.lastModifyBy }}</td>
            <td>{{ task.createdBy }}</td>
            <td>{{ task.notes }}</td>
            <td>
              <div v-if="task.taskStatus">
                <router-link :to="{ name: 'TaskStatusView', params: { taskStatusId: task.taskStatus.id } }">{{
                  task.taskStatus.code
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="task.taskMoM">
                <router-link :to="{ name: 'MoMView', params: { moMId: task.taskMoM.id } }">{{ task.taskMoM.title }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="task.projectName">
                <router-link :to="{ name: 'ProjectView', params: { projectId: task.projectName.id } }">{{
                  task.projectName.code
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="task.taskOwner">
                <router-link :to="{ name: 'PersonView', params: { personId: task.taskOwner.id } }">{{ task.taskOwner.name }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TaskView', params: { taskId: task.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TaskEdit', params: { taskId: task.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(task)"
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
        ><span id="proAppsApp.task.delete.question" data-cy="taskDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-task-heading" v-text="$t('proAppsApp.task.delete.question', { id: removeId })">
          Are you sure you want to delete this Task?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-task"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTask()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./task.component.ts"></script>
