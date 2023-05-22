<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.projectCategory.home.createOrEditLabel"
          data-cy="ProjectCategoryCreateUpdateHeading"
          v-text="$t('proAppsApp.projectCategory.home.createOrEditLabel')"
        >
          Create or edit a ProjectCategory
        </h2>
        <div>
          <div class="form-group" v-if="projectCategory.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectCategory.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectCategory.code')" for="project-category-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="project-category-code"
              data-cy="code"
              :class="{ valid: !$v.projectCategory.code.$invalid, invalid: $v.projectCategory.code.$invalid }"
              v-model="$v.projectCategory.code.$model"
              required
            />
            <div v-if="$v.projectCategory.code.$anyDirty && $v.projectCategory.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.projectCategory.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectCategory.category')" for="project-category-category"
              >Category</label
            >
            <input
              type="text"
              class="form-control"
              name="category"
              id="project-category-category"
              data-cy="category"
              :class="{ valid: !$v.projectCategory.category.$invalid, invalid: $v.projectCategory.category.$invalid }"
              v-model="$v.projectCategory.category.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectCategory.dateCreation')" for="project-category-dateCreation"
              >Date Creation</label
            >
            <div class="d-flex">
              <input
                id="project-category-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.projectCategory.dateCreation.$invalid, invalid: $v.projectCategory.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.projectCategory.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectCategory.dateModify')" for="project-category-dateModify"
              >Date Modify</label
            >
            <div class="d-flex">
              <input
                id="project-category-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.projectCategory.dateModify.$invalid, invalid: $v.projectCategory.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.projectCategory.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectCategory.lastModifyBy')" for="project-category-lastModifyBy"
              >Last Modify By</label
            >
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="project-category-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.projectCategory.lastModifyBy.$invalid, invalid: $v.projectCategory.lastModifyBy.$invalid }"
              v-model="$v.projectCategory.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectCategory.createdBy')" for="project-category-createdBy"
              >Created By</label
            >
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="project-category-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.projectCategory.createdBy.$invalid, invalid: $v.projectCategory.createdBy.$invalid }"
              v-model="$v.projectCategory.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.projectCategory.notes')" for="project-category-notes">Notes</label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="project-category-notes"
              data-cy="notes"
              :class="{ valid: !$v.projectCategory.notes.$invalid, invalid: $v.projectCategory.notes.$invalid }"
              v-model="$v.projectCategory.notes.$model"
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
            :disabled="$v.projectCategory.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./project-category-update.component.ts"></script>
