<template>
  <div>
    <h2 id="page-heading" data-cy="MoMHeading">
      <span class="$blue-100">{{ moMS.length }}</span>
      <span v-text="$t('proAppsApp.moM.home.title')" id="mo-m-heading">Minutes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.moM.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'MoMCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-mo-m">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.moM.home.createLabel')"> Create a new Mo M </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && moMS && moMS.length === 0">
      <span v-text="$t('proAppsApp.moM.home.notFound')">No moMS found</span>
    </div>
    <div class="table-responsive" v-if="moMS && moMS.length > 0">
      <table class="table table-striped" aria-describedby="moMS">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.moM.title')">Title</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.moM.meetingDate')">Meeting Date</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.moM.minutesWritingDate')">Minutes Writing Date</span></th>
            <!--
               <th scope="row"><span v-text="$t('proAppsApp.moM.meetingObjectif')">Meeting Objectif</span></th>
               <th scope="row"><span v-text="$t('proAppsApp.moM.meetingDocReferences')">Meeting Doc References</span></th>
            -->
            <th scope="row"><span v-text="$t('proAppsApp.moM.isMoMActionsClosed')">MoM Managed ?</span></th>
            <!--
            <th scope="row"><span v-text="$t('proAppsApp.moM.subjectDecisions')">Subject Decisions</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.moM.meetingPhotoCapture')">Meeting Photo Capture</span></th>
          
            <th scope="row"><span v-text="$t('proAppsApp.moM.dateCreation')">Date Creation</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.moM.dateModify')">Date Modify</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.moM.lastModifyBy')">Last Modify By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.moM.createdBy')">Created By</span></th>
           
            <th scope="row"><span v-text="$t('proAppsApp.moM.attendees')">Attendees</span></th>
           
            <th scope="row"><span v-text="$t('proAppsApp.moM.notes')">Notes</span></th>
             -->
            <th scope="row"><span v-text="$t('proAppsApp.moM.projet')">Projet</span></th>
            <th scope="row"></th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="moM in moMS" :key="moM.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MoMView', params: { moMId: moM.id } }">{{ moM.id }}</router-link>
            </td>
            <td>
              <b>{{ moM.title }}</b>
            </td>
            <td>{{ moM.meetingDate }}</td>
            <td>{{ moM.minutesWritingDate }}</td>
            <!--<td>{{ moM.meetingObjectif }}</td> 
            <td>{{ moM.meetingDocReferences }}</td>
            -->

            <td>
              <div v-if="!moM.isMoMActionsClosed">
                <div v-if="isStatusMoMDelayed(moM.meetingDate)">
                  <span style="color: red"> <font-awesome-icon icon="bell"></font-awesome-icon> {{ getDayDiff(moM.meetingDate) }} </span>
                </div>
                <div v-else style="color: blue">{{ getDayDiff(moM.meetingDate) }}</div>
              </div>

              <div v-else style="color: forestgreen">
                Action(s) traitée(s)
                <!--<font-awesome-icon icon="check"></font-awesome-icon> -->
              </div>
            </td>

            <!-- <td>{{ moM.subjectDecisions }}</td> -->

            <!--
            <td>{{ moM.dateCreation ? $d(Date.parse(moM.dateCreation), 'short') : '' }}</td>
            <td>{{ moM.dateModify ? $d(Date.parse(moM.dateModify), 'short') : '' }}</td>
            <td>{{ moM.lastModifyBy }}</td>
            <td>{{ moM.createdBy }}</td>
           
            <td>{{ moM.attendees }}</td>
            <td>{{ moM.notes }}</td>
             -->
            <td>
              <div v-if="moM.projet">
                <router-link :to="{ name: 'ProjectView', params: { projectId: moM.projet.id } }">{{ moM.projet.code }}</router-link>
              </div>
            </td>

            <td class="text-right">
              <div class="btn-group">
                <!--
                <router-link :to="{ name: 'MoMView', params: { moMId: moM.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                -->
                <router-link :to="{ name: 'MoMEdit', params: { moMId: moM.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(moM)"
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
        ><span id="proAppsApp.moM.delete.question" data-cy="moMDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-moM-heading" v-text="$t('proAppsApp.moM.delete.question', { id: removeId })">
          Are you sure you want to delete this Mo M?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-moM"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeMoM()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./mo-m.component.ts"></script>
