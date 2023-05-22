<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.taskStatus.home.createOrEditLabel"
          data-cy="TaskStatusCreateUpdateHeading"
          v-text="$t('proAppsApp.taskStatus.home.createOrEditLabel')"
        >
          Create or edit a TaskStatus
        </h2>
        <div>
          <div class="form-group" v-if="taskStatus.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="taskStatus.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskStatus.code')" for="task-status-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="task-status-code"
              data-cy="code"
              :class="{ valid: !$v.taskStatus.code.$invalid, invalid: $v.taskStatus.code.$invalid }"
              v-model="$v.taskStatus.code.$model"
              required
            />
            <div v-if="$v.taskStatus.code.$anyDirty && $v.taskStatus.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.taskStatus.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskStatus.status')" for="task-status-status">Status</label>
            <input
              type="text"
              class="form-control"
              name="status"
              id="task-status-status"
              data-cy="status"
              :class="{ valid: !$v.taskStatus.status.$invalid, invalid: $v.taskStatus.status.$invalid }"
              v-model="$v.taskStatus.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskStatus.dateCreation')" for="task-status-dateCreation"
              >Date Creation</label
            >
            <div class="d-flex">
              <input
                id="task-status-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.taskStatus.dateCreation.$invalid, invalid: $v.taskStatus.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.taskStatus.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskStatus.dateModify')" for="task-status-dateModify"
              >Date Modify</label
            >
            <div class="d-flex">
              <input
                id="task-status-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.taskStatus.dateModify.$invalid, invalid: $v.taskStatus.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.taskStatus.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskStatus.lastModifyBy')" for="task-status-lastModifyBy"
              >Last Modify By</label
            >
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="task-status-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.taskStatus.lastModifyBy.$invalid, invalid: $v.taskStatus.lastModifyBy.$invalid }"
              v-model="$v.taskStatus.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskStatus.createdBy')" for="task-status-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="task-status-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.taskStatus.createdBy.$invalid, invalid: $v.taskStatus.createdBy.$invalid }"
              v-model="$v.taskStatus.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskStatus.notes')" for="task-status-notes">Notes</label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="task-status-notes"
              data-cy="notes"
              :class="{ valid: !$v.taskStatus.notes.$invalid, invalid: $v.taskStatus.notes.$invalid }"
              v-model="$v.taskStatus.notes.$model"
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
            :disabled="$v.taskStatus.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./task-status-update.component.ts"></script>
