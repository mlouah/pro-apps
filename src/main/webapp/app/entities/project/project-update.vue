<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.project.home.createOrEditLabel"
          data-cy="ProjectCreateUpdateHeading"
          v-text="$t('proAppsApp.project.home.createOrEditLabel')"
        >
          Create or edit a Project
        </h2>
        <div>
          <div class="form-group" v-if="project.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="project.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.name')" for="project-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="project-name"
              data-cy="name"
              :class="{ valid: !$v.project.name.$invalid, invalid: $v.project.name.$invalid }"
              v-model="$v.project.name.$model"
              required
            />
            <div v-if="$v.project.name.$anyDirty && $v.project.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.project.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.code')" for="project-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="project-code"
              data-cy="code"
              :class="{ valid: !$v.project.code.$invalid, invalid: $v.project.code.$invalid }"
              v-model="$v.project.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.notes')" for="project-notes">Notes</label>
            <textarea
              class="form-control"
              name="notes"
              id="project-notes"
              data-cy="notes"
              :class="{ valid: !$v.project.notes.$invalid, invalid: $v.project.notes.$invalid }"
              v-model="$v.project.notes.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.isInternal')" for="project-isInternal">Is Internal</label>
            <input
              type="checkbox"
              class="form-check"
              name="isInternal"
              id="project-isInternal"
              data-cy="isInternal"
              :class="{ valid: !$v.project.isInternal.$invalid, invalid: $v.project.isInternal.$invalid }"
              v-model="$v.project.isInternal.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.dateCreation')" for="project-dateCreation"
              >Date Creation</label
            >
            <div class="d-flex">
              <input
                id="project-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.project.dateCreation.$invalid, invalid: $v.project.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.project.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.dateModify')" for="project-dateModify">Date Modify</label>
            <div class="d-flex">
              <input
                id="project-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.project.dateModify.$invalid, invalid: $v.project.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.project.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.lastModifyBy')" for="project-lastModifyBy"
              >Last Modify By</label
            >
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="project-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.project.lastModifyBy.$invalid, invalid: $v.project.lastModifyBy.$invalid }"
              v-model="$v.project.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.createdBy')" for="project-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="project-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.project.createdBy.$invalid, invalid: $v.project.createdBy.$invalid }"
              v-model="$v.project.createdBy.$model"
            />
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
            :disabled="$v.project.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./project-update.component.ts"></script>
