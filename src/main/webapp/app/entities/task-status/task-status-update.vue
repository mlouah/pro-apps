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
            <label class="form-control-label" v-text="$t('proAppsApp.taskStatus.statusPicto')" for="task-status-statusPicto"
              >Status Picto</label
            >
            <div>
              <img
                v-bind:src="'data:' + taskStatus.statusPictoContentType + ';base64,' + taskStatus.statusPicto"
                style="max-height: 100px"
                v-if="taskStatus.statusPicto"
                alt="taskStatus image"
              />
              <div v-if="taskStatus.statusPicto" class="form-text text-danger clearfix">
                <span class="pull-left">{{ taskStatus.statusPictoContentType }}, {{ byteSize(taskStatus.statusPicto) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('statusPicto', 'statusPictoContentType', 'file_statusPicto')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_statusPicto"
                id="file_statusPicto"
                data-cy="statusPicto"
                v-on:change="setFileData($event, taskStatus, 'statusPicto', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="statusPicto"
              id="task-status-statusPicto"
              data-cy="statusPicto"
              :class="{ valid: !$v.taskStatus.statusPicto.$invalid, invalid: $v.taskStatus.statusPicto.$invalid }"
              v-model="$v.taskStatus.statusPicto.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="statusPictoContentType"
              id="task-status-statusPictoContentType"
              v-model="taskStatus.statusPictoContentType"
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
