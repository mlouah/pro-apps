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
              required
            />
            <div v-if="$v.project.code.$anyDirty && $v.project.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.project.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.startDate')" for="project-startDate">Start Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-startDate"
                  v-model="$v.project.startDate.$model"
                  name="startDate"
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
                id="project-startDate"
                data-cy="startDate"
                type="text"
                class="form-control"
                name="startDate"
                :class="{ valid: !$v.project.startDate.$invalid, invalid: $v.project.startDate.$invalid }"
                v-model="$v.project.startDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.realEndtDate')" for="project-realEndtDate"
              >Real Endt Date</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-realEndtDate"
                  v-model="$v.project.realEndtDate.$model"
                  name="realEndtDate"
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
                id="project-realEndtDate"
                data-cy="realEndtDate"
                type="text"
                class="form-control"
                name="realEndtDate"
                :class="{ valid: !$v.project.realEndtDate.$invalid, invalid: $v.project.realEndtDate.$invalid }"
                v-model="$v.project.realEndtDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.projectDescription')" for="project-projectDescription"
              >Project Description</label
            >
            <textarea
              class="form-control"
              name="projectDescription"
              id="project-projectDescription"
              data-cy="projectDescription"
              :class="{ valid: !$v.project.projectDescription.$invalid, invalid: $v.project.projectDescription.$invalid }"
              v-model="$v.project.projectDescription.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.initialEndDate')" for="project-initialEndDate"
              >Initial End Date</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-initialEndDate"
                  v-model="$v.project.initialEndDate.$model"
                  name="initialEndDate"
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
                id="project-initialEndDate"
                data-cy="initialEndDate"
                type="text"
                class="form-control"
                name="initialEndDate"
                :class="{ valid: !$v.project.initialEndDate.$invalid, invalid: $v.project.initialEndDate.$invalid }"
                v-model="$v.project.initialEndDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.initialCost')" for="project-initialCost">Initial Cost</label>
            <input
              type="number"
              class="form-control"
              name="initialCost"
              id="project-initialCost"
              data-cy="initialCost"
              :class="{ valid: !$v.project.initialCost.$invalid, invalid: $v.project.initialCost.$invalid }"
              v-model.number="$v.project.initialCost.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.initialWorkLoad')" for="project-initialWorkLoad"
              >Initial Work Load</label
            >
            <input
              type="number"
              class="form-control"
              name="initialWorkLoad"
              id="project-initialWorkLoad"
              data-cy="initialWorkLoad"
              :class="{ valid: !$v.project.initialWorkLoad.$invalid, invalid: $v.project.initialWorkLoad.$invalid }"
              v-model.number="$v.project.initialWorkLoad.$model"
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
            <label class="form-control-label" v-text="$t('proAppsApp.project.projectPriority')" for="project-projectPriority"
              >Project Priority</label
            >
            <select
              class="form-control"
              id="project-projectPriority"
              data-cy="projectPriority"
              name="projectPriority"
              v-model="project.projectPriority"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.projectPriority && projectPriorityOption.id === project.projectPriority.id
                    ? project.projectPriority
                    : projectPriorityOption
                "
                v-for="projectPriorityOption in projectPriorities"
                :key="projectPriorityOption.id"
              >
                {{ projectPriorityOption.code }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.projectCategory')" for="project-projectCategory"
              >Project Category</label
            >
            <select
              class="form-control"
              id="project-projectCategory"
              data-cy="projectCategory"
              name="projectCategory"
              v-model="project.projectCategory"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.projectCategory && projectCategoryOption.id === project.projectCategory.id
                    ? project.projectCategory
                    : projectCategoryOption
                "
                v-for="projectCategoryOption in projectCategories"
                :key="projectCategoryOption.id"
              >
                {{ projectCategoryOption.code }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.clientCode')" for="project-clientCode">Client Code</label>
            <select class="form-control" id="project-clientCode" data-cy="clientCode" name="clientCode" v-model="project.clientCode">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="project.clientCode && clientOption.id === project.clientCode.id ? project.clientCode : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.code }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.internalProjectManager')" for="project-internalProjectManager"
              >Internal Project Manager</label
            >
            <select
              class="form-control"
              id="project-internalProjectManager"
              data-cy="internalProjectManager"
              name="internalProjectManager"
              v-model="project.internalProjectManager"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.internalProjectManager && personOption.id === project.internalProjectManager.id
                    ? project.internalProjectManager
                    : personOption
                "
                v-for="personOption in people"
                :key="personOption.id"
              >
                {{ personOption.name }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.company')" for="project-company">Company</label>
            <select class="form-control" id="project-company" data-cy="company" name="company" v-model="project.company">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="project.company && companyOption.id === project.company.id ? project.company : companyOption"
                v-for="companyOption in companies"
                :key="companyOption.id"
              >
                {{ companyOption.code }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.project.projectStatusCode')" for="project-projectStatusCode"
              >Project Status Code</label
            >
            <select
              class="form-control"
              id="project-projectStatusCode"
              data-cy="projectStatusCode"
              name="projectStatusCode"
              v-model="project.projectStatusCode"
              required
            >
              <option v-if="!project.projectStatusCode" v-bind:value="null" selected></option>
              <option
                v-bind:value="
                  project.projectStatusCode && projectStatusCodeOption.id === project.projectStatusCode.id
                    ? project.projectStatusCode
                    : projectStatusCodeOption
                "
                v-for="projectStatusCodeOption in projectStatusCodes"
                :key="projectStatusCodeOption.id"
              >
                {{ projectStatusCodeOption.code }}
              </option>
            </select>
          </div>
          <div v-if="$v.project.projectStatusCode.$anyDirty && $v.project.projectStatusCode.$invalid">
            <small class="form-text text-danger" v-if="!$v.project.projectStatusCode.required" v-text="$t('entity.validation.required')">
              This field is required.
            </small>
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
