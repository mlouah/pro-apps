<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.projectStatusCode.home.createOrEditLabel"
          data-cy="ProjectStatusCodeCreateUpdateHeading"
          v-text="$t('proAppsApp.projectStatusCode.home.createOrEditLabel')"
        >
          Create or edit a ProjectStatusCode
        </h2>
        <div>
          <div class="form-group" v-if="projectStatusCode.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectStatusCode.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatusCode.code')" for="project-status-code-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="project-status-code-code"
              data-cy="code"
              :class="{ valid: !$v.projectStatusCode.code.$invalid, invalid: $v.projectStatusCode.code.$invalid }"
              v-model="$v.projectStatusCode.code.$model"
              required
            />
            <div v-if="$v.projectStatusCode.code.$anyDirty && $v.projectStatusCode.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.projectStatusCode.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatusCode.status')" for="project-status-code-status"
              >Status</label
            >
            <input
              type="text"
              class="form-control"
              name="status"
              id="project-status-code-status"
              data-cy="status"
              :class="{ valid: !$v.projectStatusCode.status.$invalid, invalid: $v.projectStatusCode.status.$invalid }"
              v-model="$v.projectStatusCode.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('proAppsApp.projectStatusCode.dateCreation')"
              for="project-status-code-dateCreation"
              >Date Creation</label
            >
            <div class="d-flex">
              <input
                id="project-status-code-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.projectStatusCode.dateCreation.$invalid, invalid: $v.projectStatusCode.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.projectStatusCode.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatusCode.dateModify')" for="project-status-code-dateModify"
              >Date Modify</label
            >
            <div class="d-flex">
              <input
                id="project-status-code-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.projectStatusCode.dateModify.$invalid, invalid: $v.projectStatusCode.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.projectStatusCode.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('proAppsApp.projectStatusCode.lastModifyBy')"
              for="project-status-code-lastModifyBy"
              >Last Modify By</label
            >
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="project-status-code-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.projectStatusCode.lastModifyBy.$invalid, invalid: $v.projectStatusCode.lastModifyBy.$invalid }"
              v-model="$v.projectStatusCode.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatusCode.createdBy')" for="project-status-code-createdBy"
              >Created By</label
            >
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="project-status-code-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.projectStatusCode.createdBy.$invalid, invalid: $v.projectStatusCode.createdBy.$invalid }"
              v-model="$v.projectStatusCode.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatusCode.notes')" for="project-status-code-notes"
              >Notes</label
            >
            <input
              type="text"
              class="form-control"
              name="notes"
              id="project-status-code-notes"
              data-cy="notes"
              :class="{ valid: !$v.projectStatusCode.notes.$invalid, invalid: $v.projectStatusCode.notes.$invalid }"
              v-model="$v.projectStatusCode.notes.$model"
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
            :disabled="$v.projectStatusCode.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./project-status-code-update.component.ts"></script>
