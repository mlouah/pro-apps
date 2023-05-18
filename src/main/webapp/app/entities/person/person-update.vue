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
