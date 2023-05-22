<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.person.home.createOrEditLabel"
          data-cy="PersonCreateUpdateHeading"
          v-text="$t('proAppsApp.person.home.createOrEditLabel')"
        >
          Create or edit a Person
        </h2>
        <div>
          <div class="form-group" v-if="person.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="person.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.person.name')" for="person-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="person-name"
              data-cy="name"
              :class="{ valid: !$v.person.name.$invalid, invalid: $v.person.name.$invalid }"
              v-model="$v.person.name.$model"
              required
            />
            <div v-if="$v.person.name.$anyDirty && $v.person.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.person.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.person.code')" for="person-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="person-code"
              data-cy="code"
              :class="{ valid: !$v.person.code.$invalid, invalid: $v.person.code.$invalid }"
              v-model="$v.person.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.person.isExternal')" for="person-isExternal">Is External</label>
            <input
              type="checkbox"
              class="form-check"
              name="isExternal"
              id="person-isExternal"
              data-cy="isExternal"
              :class="{ valid: !$v.person.isExternal.$invalid, invalid: $v.person.isExternal.$invalid }"
              v-model="$v.person.isExternal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.person.dateCreation')" for="person-dateCreation">Date Creation</label>
            <div class="d-flex">
              <input
                id="person-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.person.dateCreation.$invalid, invalid: $v.person.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.person.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.person.dateModify')" for="person-dateModify">Date Modify</label>
            <div class="d-flex">
              <input
                id="person-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.person.dateModify.$invalid, invalid: $v.person.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.person.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.person.lastModifyBy')" for="person-lastModifyBy">Last Modify By</label>
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="person-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.person.lastModifyBy.$invalid, invalid: $v.person.lastModifyBy.$invalid }"
              v-model="$v.person.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.person.createdBy')" for="person-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="person-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.person.createdBy.$invalid, invalid: $v.person.createdBy.$invalid }"
              v-model="$v.person.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.person.notes')" for="person-notes">Notes</label>
            <textarea
              class="form-control"
              name="notes"
              id="person-notes"
              data-cy="notes"
              :class="{ valid: !$v.person.notes.$invalid, invalid: $v.person.notes.$invalid }"
              v-model="$v.person.notes.$model"
            ></textarea>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.person.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./person-update.component.ts"></script>
