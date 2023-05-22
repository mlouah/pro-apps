<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.projectStatus.home.createOrEditLabel"
          data-cy="ProjectStatusCreateUpdateHeading"
          v-text="$t('proAppsApp.projectStatus.home.createOrEditLabel')"
        >
          Create or edit a ProjectStatus
        </h2>
        <div>
          <div class="form-group" v-if="projectStatus.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectStatus.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.statusDate')" for="project-status-statusDate"
              >Status Date</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-status-statusDate"
                  v-model="$v.projectStatus.statusDate.$model"
                  name="statusDate"
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
                id="project-status-statusDate"
                data-cy="statusDate"
                type="text"
                class="form-control"
                name="statusDate"
                :class="{ valid: !$v.projectStatus.statusDate.$invalid, invalid: $v.projectStatus.statusDate.$invalid }"
                v-model="$v.projectStatus.statusDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.statusTitle')" for="project-status-statusTitle"
              >Status Title</label
            >
            <input
              type="text"
              class="form-control"
              name="statusTitle"
              id="project-status-statusTitle"
              data-cy="statusTitle"
              :class="{ valid: !$v.projectStatus.statusTitle.$invalid, invalid: $v.projectStatus.statusTitle.$invalid }"
              v-model="$v.projectStatus.statusTitle.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.updatedProgress')" for="project-status-updatedProgress"
              >Updated Progress</label
            >
            <input
              type="number"
              class="form-control"
              name="updatedProgress"
              id="project-status-updatedProgress"
              data-cy="updatedProgress"
              :class="{ valid: !$v.projectStatus.updatedProgress.$invalid, invalid: $v.projectStatus.updatedProgress.$invalid }"
              v-model.number="$v.projectStatus.updatedProgress.$model"
            />
            <div v-if="$v.projectStatus.updatedProgress.$anyDirty && $v.projectStatus.updatedProgress.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.projectStatus.updatedProgress.min"
                v-text="$t('entity.validation.min', { min: 0 })"
              >
                This field should be at least 0.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.projectStatus.updatedProgress.max"
                v-text="$t('entity.validation.max', { max: 100 })"
              >
                This field cannot be longer than 100 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.projectStatus.updatedProgress.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('proAppsApp.projectStatus.updatedWorkloadEAC')"
              for="project-status-updatedWorkloadEAC"
              >Updated Workload EAC</label
            >
            <input
              type="number"
              class="form-control"
              name="updatedWorkloadEAC"
              id="project-status-updatedWorkloadEAC"
              data-cy="updatedWorkloadEAC"
              :class="{ valid: !$v.projectStatus.updatedWorkloadEAC.$invalid, invalid: $v.projectStatus.updatedWorkloadEAC.$invalid }"
              v-model.number="$v.projectStatus.updatedWorkloadEAC.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.updatedEndDate')" for="project-status-updatedEndDate"
              >Updated End Date</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-status-updatedEndDate"
                  v-model="$v.projectStatus.updatedEndDate.$model"
                  name="updatedEndDate"
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
                id="project-status-updatedEndDate"
                data-cy="updatedEndDate"
                type="text"
                class="form-control"
                name="updatedEndDate"
                :class="{ valid: !$v.projectStatus.updatedEndDate.$invalid, invalid: $v.projectStatus.updatedEndDate.$invalid }"
                v-model="$v.projectStatus.updatedEndDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.updatedCost')" for="project-status-updatedCost"
              >Updated Cost</label
            >
            <input
              type="number"
              class="form-control"
              name="updatedCost"
              id="project-status-updatedCost"
              data-cy="updatedCost"
              :class="{ valid: !$v.projectStatus.updatedCost.$invalid, invalid: $v.projectStatus.updatedCost.$invalid }"
              v-model.number="$v.projectStatus.updatedCost.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.dateCreation')" for="project-status-dateCreation"
              >Date Creation</label
            >
            <div class="d-flex">
              <input
                id="project-status-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.projectStatus.dateCreation.$invalid, invalid: $v.projectStatus.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.projectStatus.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.dateModify')" for="project-status-dateModify"
              >Date Modify</label
            >
            <div class="d-flex">
              <input
                id="project-status-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.projectStatus.dateModify.$invalid, invalid: $v.projectStatus.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.projectStatus.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.lastModifyBy')" for="project-status-lastModifyBy"
              >Last Modify By</label
            >
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="project-status-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.projectStatus.lastModifyBy.$invalid, invalid: $v.projectStatus.lastModifyBy.$invalid }"
              v-model="$v.projectStatus.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.createdBy')" for="project-status-createdBy"
              >Created By</label
            >
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="project-status-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.projectStatus.createdBy.$invalid, invalid: $v.projectStatus.createdBy.$invalid }"
              v-model="$v.projectStatus.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.notes')" for="project-status-notes">Notes</label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="project-status-notes"
              data-cy="notes"
              :class="{ valid: !$v.projectStatus.notes.$invalid, invalid: $v.projectStatus.notes.$invalid }"
              v-model="$v.projectStatus.notes.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectStatus.projectName')" for="project-status-projectName"
              >Project Name</label
            >
            <select
              class="form-control"
              id="project-status-projectName"
              data-cy="projectName"
              name="projectName"
              v-model="projectStatus.projectName"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectStatus.projectName && projectOption.id === projectStatus.projectName.id ? projectStatus.projectName : projectOption
                "
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.code }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('proAppsApp.projectStatus.projectStatusCode')"
              for="project-status-projectStatusCode"
              >Project Status Code</label
            >
            <select
              class="form-control"
              id="project-status-projectStatusCode"
              data-cy="projectStatusCode"
              name="projectStatusCode"
              v-model="projectStatus.projectStatusCode"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectStatus.projectStatusCode && projectStatusCodeOption.id === projectStatus.projectStatusCode.id
                    ? projectStatus.projectStatusCode
                    : projectStatusCodeOption
                "
                v-for="projectStatusCodeOption in projectStatusCodes"
                :key="projectStatusCodeOption.id"
              >
                {{ projectStatusCodeOption.code }}
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
            :disabled="$v.projectStatus.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./project-status-update.component.ts"></script>
