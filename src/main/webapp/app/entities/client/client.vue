<template>
  <div>
    <h2 id="page-heading" data-cy="ClientHeading">
      <span v-text="$t('proAppsApp.client.home.title')" id="client-heading">Clients</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.client.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ClientCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-client"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.client.home.createLabel')"> Create a new Client </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && clients && clients.length === 0">
      <span v-text="$t('proAppsApp.client.home.notFound')">No clients found</span>
    </div>
    <div class="table-responsive" v-if="clients && clients.length > 0">
      <table class="table table-striped" aria-describedby="clients">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.name')">Name</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.code')">Code</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.clientLogo')">Client Logo</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.url')">Url</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.dateCreation')">Date Creation</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.dateModify')">Date Modify</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.lastModifyBy')">Last Modify By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.createdBy')">Created By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.client.notes')">Notes</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="client in clients" :key="client.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClientView', params: { clientId: client.id } }">{{ client.id }}</router-link>
            </td>
            <td>{{ client.name }}</td>
            <td>{{ client.code }}</td>
            <td>
              <a v-if="client.clientLogo" v-on:click="openFile(client.clientLogoContentType, client.clientLogo)">
                <img
                  v-bind:src="'data:' + client.clientLogoContentType + ';base64,' + client.clientLogo"
                  style="max-height: 30px"
                  alt="client image"
                />
              </a>
              <span v-if="client.clientLogo">{{ client.clientLogoContentType }}, {{ byteSize(client.clientLogo) }}</span>
            </td>
            <td>{{ client.url }}</td>
            <td>{{ client.dateCreation ? $d(Date.parse(client.dateCreation), 'short') : '' }}</td>
            <td>{{ client.dateModify ? $d(Date.parse(client.dateModify), 'short') : '' }}</td>
            <td>{{ client.lastModifyBy }}</td>
            <td>{{ client.createdBy }}</td>
            <td>{{ client.notes }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ClientView', params: { clientId: client.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(client)"
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
        ><span id="proAppsApp.client.delete.question" data-cy="clientDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-client-heading" v-text="$t('proAppsApp.client.delete.question', { id: removeId })">
          Are you sure you want to delete this Client?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-client"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeClient()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./client.component.ts"></script>
