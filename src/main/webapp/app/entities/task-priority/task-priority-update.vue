<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.taskPriority.home.createOrEditLabel"
          data-cy="TaskPriorityCreateUpdateHeading"
          v-text="$t('proAppsApp.taskPriority.home.createOrEditLabel')"
        >
          Create or edit a TaskPriority
        </h2>
        <div>
          <div class="form-group" v-if="taskPriority.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="taskPriority.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskPriority.code')" for="task-priority-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="task-priority-code"
              data-cy="code"
              :class="{ valid: !$v.taskPriority.code.$invalid, invalid: $v.taskPriority.code.$invalid }"
              v-model="$v.taskPriority.code.$model"
              required
            />
            <div v-if="$v.taskPriority.code.$anyDirty && $v.taskPriority.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.taskPriority.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskPriority.priority')" for="task-priority-priority">Priority</label>
            <input
              type="text"
              class="form-control"
              name="priority"
              id="task-priority-priority"
              data-cy="priority"
              :class="{ valid: !$v.taskPriority.priority.$invalid, invalid: $v.taskPriority.priority.$invalid }"
              v-model="$v.taskPriority.priority.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskPriority.taskPriorityPicto')" for="task-priority-taskPriorityPicto"
              >Task Priority Picto</label
            >
            <div>
              <img
                v-bind:src="'data:' + taskPriority.taskPriorityPictoContentType + ';base64,' + taskPriority.taskPriorityPicto"
                style="max-height: 100px"
                v-if="taskPriority.taskPriorityPicto"
                alt="taskPriority image"
              />
              <div v-if="taskPriority.taskPriorityPicto" class="form-text text-danger clearfix">
                <span class="pull-left"
                  >{{ taskPriority.taskPriorityPictoContentType }}, {{ byteSize(taskPriority.taskPriorityPicto) }}</span
                >
                <button
                  type="button"
                  v-on:click="clearInputImage('taskPriorityPicto', 'taskPriorityPictoContentType', 'file_taskPriorityPicto')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_taskPriorityPicto"
                id="file_taskPriorityPicto"
                data-cy="taskPriorityPicto"
                v-on:change="setFileData($event, taskPriority, 'taskPriorityPicto', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="taskPriorityPicto"
              id="task-priority-taskPriorityPicto"
              data-cy="taskPriorityPicto"
              :class="{ valid: !$v.taskPriority.taskPriorityPicto.$invalid, invalid: $v.taskPriority.taskPriorityPicto.$invalid }"
              v-model="$v.taskPriority.taskPriorityPicto.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="taskPriorityPictoContentType"
              id="task-priority-taskPriorityPictoContentType"
              v-model="taskPriority.taskPriorityPictoContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.taskPriority.notes')" for="task-priority-notes">Notes</label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="task-priority-notes"
              data-cy="notes"
              :class="{ valid: !$v.taskPriority.notes.$invalid, invalid: $v.taskPriority.notes.$invalid }"
              v-model="$v.taskPriority.notes.$model"
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
            :disabled="$v.taskPriority.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./task-priority-update.component.ts"></script>
