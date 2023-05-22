<template>
  <div>
    <h2 id="page-heading" data-cy="PersonHeading">
      <span v-text="$t('proAppsApp.person.home.title')" id="person-heading">People</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('proAppsApp.person.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'PersonCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-person"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('proAppsApp.person.home.createLabel')"> Create a new Person </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && people && people.length === 0">
      <span v-text="$t('proAppsApp.person.home.notFound')">No people found</span>
    </div>
    <div class="table-responsive" v-if="people && people.length > 0">
      <table class="table table-striped" aria-describedby="people">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.person.name')">Name</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.person.code')">Code</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.person.isExternal')">Is External</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.person.dateCreation')">Date Creation</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.person.dateModify')">Date Modify</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.person.lastModifyBy')">Last Modify By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.person.createdBy')">Created By</span></th>
            <th scope="row"><span v-text="$t('proAppsApp.person.notes')">Notes</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="person in people" :key="person.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PersonView', params: { personId: person.id } }">{{ person.id }}</router-link>
            </td>
            <td>{{ person.name }}</td>
            <td>{{ person.code }}</td>
            <td>{{ person.isExternal }}</td>
            <td>{{ person.dateCreation ? $d(Date.parse(person.dateCreation), 'short') : '' }}</td>
            <td>{{ person.dateModify ? $d(Date.parse(person.dateModify), 'short') : '' }}</td>
            <td>{{ person.lastModifyBy }}</td>
            <td>{{ person.createdBy }}</td>
            <td>{{ person.notes }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PersonView', params: { personId: person.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PersonEdit', params: { personId: person.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(person)"
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
        ><span id="proAppsApp.person.delete.question" data-cy="personDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-person-heading" v-text="$t('proAppsApp.person.delete.question', { id: removeId })">
          Are you sure you want to delete this Person?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-person"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removePerson()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./person.component.ts"></script>
