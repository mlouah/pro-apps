<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.company.home.createOrEditLabel"
          data-cy="CompanyCreateUpdateHeading"
          v-text="$t('proAppsApp.company.home.createOrEditLabel')"
        >
          Create or edit a Company
        </h2>
        <div>
          <div class="form-group" v-if="company.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="company.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.name')" for="company-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="company-name"
              data-cy="name"
              :class="{ valid: !$v.company.name.$invalid, invalid: $v.company.name.$invalid }"
              v-model="$v.company.name.$model"
              required
            />
            <div v-if="$v.company.name.$anyDirty && $v.company.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.company.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.code')" for="company-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="company-code"
              data-cy="code"
              :class="{ valid: !$v.company.code.$invalid, invalid: $v.company.code.$invalid }"
              v-model="$v.company.code.$model"
              required
            />
            <div v-if="$v.company.code.$anyDirty && $v.company.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.company.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.companyProfile')" for="company-companyProfile"
              >Company Profile</label
            >
            <textarea
              class="form-control"
              name="companyProfile"
              id="company-companyProfile"
              data-cy="companyProfile"
              :class="{ valid: !$v.company.companyProfile.$invalid, invalid: $v.company.companyProfile.$invalid }"
              v-model="$v.company.companyProfile.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.url')" for="company-url">Url</label>
            <input
              type="text"
              class="form-control"
              name="url"
              id="company-url"
              data-cy="url"
              :class="{ valid: !$v.company.url.$invalid, invalid: $v.company.url.$invalid }"
              v-model="$v.company.url.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.logo')" for="company-logo">Logo</label>
            <div>
              <img
                v-bind:src="'data:' + company.logoContentType + ';base64,' + company.logo"
                style="max-height: 100px"
                v-if="company.logo"
                alt="company image"
              />
              <div v-if="company.logo" class="form-text text-danger clearfix">
                <span class="pull-left">{{ company.logoContentType }}, {{ byteSize(company.logo) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('logo', 'logoContentType', 'file_logo')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_logo"
                id="file_logo"
                data-cy="logo"
                v-on:change="setFileData($event, company, 'logo', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="logo"
              id="company-logo"
              data-cy="logo"
              :class="{ valid: !$v.company.logo.$invalid, invalid: $v.company.logo.$invalid }"
              v-model="$v.company.logo.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="logoContentType"
              id="company-logoContentType"
              v-model="company.logoContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.dateCreation')" for="company-dateCreation"
              >Date Creation</label
            >
            <div class="d-flex">
              <input
                id="company-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.company.dateCreation.$invalid, invalid: $v.company.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.company.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.dateModify')" for="company-dateModify">Date Modify</label>
            <div class="d-flex">
              <input
                id="company-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.company.dateModify.$invalid, invalid: $v.company.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.company.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.lastModifyBy')" for="company-lastModifyBy"
              >Last Modify By</label
            >
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="company-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.company.lastModifyBy.$invalid, invalid: $v.company.lastModifyBy.$invalid }"
              v-model="$v.company.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.createdBy')" for="company-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="company-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.company.createdBy.$invalid, invalid: $v.company.createdBy.$invalid }"
              v-model="$v.company.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.company.notes')" for="company-notes">Notes</label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="company-notes"
              data-cy="notes"
              :class="{ valid: !$v.company.notes.$invalid, invalid: $v.company.notes.$invalid }"
              v-model="$v.company.notes.$model"
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
            :disabled="$v.company.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./company-update.component.ts"></script>
