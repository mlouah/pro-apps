<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.task.home.createOrEditLabel"
          data-cy="TaskCreateUpdateHeading"
          v-text="$t('proAppsApp.task.home.createOrEditLabel')"
        >
          Create or edit a Task
        </h2>
        <div>
          <div class="form-group" v-if="task.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="task.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.subject')" for="task-subject">Subject</label>
            <input
              type="text"
              class="form-control"
              name="subject"
              id="task-subject"
              data-cy="subject"
              :class="{ valid: !$v.task.subject.$invalid, invalid: $v.task.subject.$invalid }"
              v-model="$v.task.subject.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.taskTitle')" for="task-taskTitle">Task Title</label>
            <input
              type="text"
              class="form-control"
              name="taskTitle"
              id="task-taskTitle"
              data-cy="taskTitle"
              :class="{ valid: !$v.task.taskTitle.$invalid, invalid: $v.task.taskTitle.$invalid }"
              v-model="$v.task.taskTitle.$model"
              required
            />
            <div v-if="$v.task.taskTitle.$anyDirty && $v.task.taskTitle.$invalid">
              <small class="form-text text-danger" v-if="!$v.task.taskTitle.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.dealLine')" for="task-dealLine">Deal Line</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="task-dealLine"
                  v-model="$v.task.dealLine.$model"
                  name="dealLine"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="task-dealLine"
                data-cy="dealLine"
                type="text"
                class="form-control"
                name="dealLine"
                :class="{ valid: !$v.task.dealLine.$invalid, invalid: $v.task.dealLine.$invalid }"
                v-model="$v.task.dealLine.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.isUrgent')" for="task-isUrgent">Is Urgent</label>
            <input
              type="checkbox"
              class="form-check"
              name="isUrgent"
              id="task-isUrgent"
              data-cy="isUrgent"
              :class="{ valid: !$v.task.isUrgent.$invalid, invalid: $v.task.isUrgent.$invalid }"
              v-model="$v.task.isUrgent.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.dateCreation')" for="task-dateCreation">Date Creation</label>
            <div class="d-flex">
              <input
                id="task-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.task.dateCreation.$invalid, invalid: $v.task.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.task.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.dateModify')" for="task-dateModify">Date Modify</label>
            <div class="d-flex">
              <input
                id="task-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.task.dateModify.$invalid, invalid: $v.task.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.task.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.lastModifyBy')" for="task-lastModifyBy">Last Modify By</label>
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="task-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.task.lastModifyBy.$invalid, invalid: $v.task.lastModifyBy.$invalid }"
              v-model="$v.task.lastModifyBy.$model"
            />
          </div>

          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.createdBy')" for="task-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="task-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.task.createdBy.$invalid, invalid: $v.task.createdBy.$invalid }"
              v-model="$v.task.createdBy.$model"
            />
          </div>
          <!-- start Close date-->

          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.closeDate')" for="task-dealLine">Close date </label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="task-closeDate"
                  v-model="$v.task.closeDate.$model"
                  name="closeDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="task-closeDate"
                data-cy="closeDate"
                type="text"
                class="form-control"
                name="closeDate"
                :class="{ valid: !$v.task.closeDate.$invalid, invalid: $v.task.closeDate.$invalid }"
                v-model="$v.task.closeDate.$model"
              />
            </b-input-group>
          </div>

          <!-- end Close  date-->

          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.notes')" for="task-notes">Notes</label>
            <textarea
              class="form-control"
              name="notes"
              id="task-notes"
              data-cy="notes"
              :class="{ valid: !$v.task.notes.$invalid, invalid: $v.task.notes.$invalid }"
              v-model="$v.task.notes.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.taskStatus')" for="task-taskStatus">Task Status</label>
            <select class="form-control" id="task-taskStatus" data-cy="taskStatus" name="taskStatus" v-model="task.taskStatus">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="task.taskStatus && taskStatusOption.id === task.taskStatus.id ? task.taskStatus : taskStatusOption"
                v-for="taskStatusOption in taskStatuses"
                :key="taskStatusOption.id"
              >
                {{ taskStatusOption.code }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.taskMoM')" for="task-taskMoM">Task Mo M</label>
            <select class="form-control" id="task-taskMoM" data-cy="taskMoM" name="taskMoM" v-model="task.taskMoM">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="task.taskMoM && moMOption.id === task.taskMoM.id ? task.taskMoM : moMOption"
                v-for="moMOption in moMS"
                :key="moMOption.id"
              >
                {{ moMOption.title }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.projectName')" for="task-projectName">Project Name</label>
            <select class="form-control" id="task-projectName" data-cy="projectName" name="projectName" v-model="task.projectName">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="task.projectName && projectOption.id === task.projectName.id ? task.projectName : projectOption"
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.code }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.task.taskOwner')" for="task-taskOwner">Task Owner</label>
            <select class="form-control" id="task-taskOwner" data-cy="taskOwner" name="taskOwner" v-model="task.taskOwner">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="task.taskOwner && personOption.id === task.taskOwner.id ? task.taskOwner : personOption"
                v-for="personOption in people"
                :key="personOption.id"
              >
                {{ personOption.name }}
              </option>
            </select>
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
            :disabled="$v.task.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./task-update.component.ts"></script>
