<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.projectPriority.home.createOrEditLabel"
          data-cy="ProjectPriorityCreateUpdateHeading"
          v-text="$t('proAppsApp.projectPriority.home.createOrEditLabel')"
        >
          Create or edit a ProjectPriority
        </h2>
        <div>
          <div class="form-group" v-if="projectPriority.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectPriority.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectPriority.code')" for="project-priority-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="project-priority-code"
              data-cy="code"
              :class="{ valid: !$v.projectPriority.code.$invalid, invalid: $v.projectPriority.code.$invalid }"
              v-model="$v.projectPriority.code.$model"
              required
            />
            <div v-if="$v.projectPriority.code.$anyDirty && $v.projectPriority.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.projectPriority.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectPriority.priority')" for="project-priority-priority"
              >Priority</label
            >
            <input
              type="text"
              class="form-control"
              name="priority"
              id="project-priority-priority"
              data-cy="priority"
              :class="{ valid: !$v.projectPriority.priority.$invalid, invalid: $v.projectPriority.priority.$invalid }"
              v-model="$v.projectPriority.priority.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectPriority.dateCreation')" for="project-priority-dateCreation"
              >Date Creation</label
            >
            <div class="d-flex">
              <input
                id="project-priority-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.projectPriority.dateCreation.$invalid, invalid: $v.projectPriority.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.projectPriority.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectPriority.dateModify')" for="project-priority-dateModify"
              >Date Modify</label
            >
            <div class="d-flex">
              <input
                id="project-priority-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.projectPriority.dateModify.$invalid, invalid: $v.projectPriority.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.projectPriority.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectPriority.lastModifyBy')" for="project-priority-lastModifyBy"
              >Last Modify By</label
            >
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="project-priority-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.projectPriority.lastModifyBy.$invalid, invalid: $v.projectPriority.lastModifyBy.$invalid }"
              v-model="$v.projectPriority.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectPriority.createdBy')" for="project-priority-createdBy"
              >Created By</label
            >
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="project-priority-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.projectPriority.createdBy.$invalid, invalid: $v.projectPriority.createdBy.$invalid }"
              v-model="$v.projectPriority.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectPriority.notes')" for="project-priority-notes">Notes</label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="project-priority-notes"
              data-cy="notes"
              :class="{ valid: !$v.projectPriority.notes.$invalid, invalid: $v.projectPriority.notes.$invalid }"
              v-model="$v.projectPriority.notes.$model"
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
            :disabled="$v.projectPriority.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./project-priority-update.component.ts"></script>
