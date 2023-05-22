<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.client.home.createOrEditLabel"
          data-cy="ClientCreateUpdateHeading"
          v-text="$t('proAppsApp.client.home.createOrEditLabel')"
        >
          Create or edit a Client
        </h2>
        <div>
          <div class="form-group" v-if="client.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="client.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.name')" for="client-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="client-name"
              data-cy="name"
              :class="{ valid: !$v.client.name.$invalid, invalid: $v.client.name.$invalid }"
              v-model="$v.client.name.$model"
              required
            />
            <div v-if="$v.client.name.$anyDirty && $v.client.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.code')" for="client-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="client-code"
              data-cy="code"
              :class="{ valid: !$v.client.code.$invalid, invalid: $v.client.code.$invalid }"
              v-model="$v.client.code.$model"
              required
            />
            <div v-if="$v.client.code.$anyDirty && $v.client.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.clientLogo')" for="client-clientLogo">Client Logo</label>
            <div>
              <img
                v-bind:src="'data:' + client.clientLogoContentType + ';base64,' + client.clientLogo"
                style="max-height: 100px"
                v-if="client.clientLogo"
                alt="client image"
              />
              <div v-if="client.clientLogo" class="form-text text-danger clearfix">
                <span class="pull-left">{{ client.clientLogoContentType }}, {{ byteSize(client.clientLogo) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('clientLogo', 'clientLogoContentType', 'file_clientLogo')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_clientLogo"
                id="file_clientLogo"
                data-cy="clientLogo"
                v-on:change="setFileData($event, client, 'clientLogo', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="clientLogo"
              id="client-clientLogo"
              data-cy="clientLogo"
              :class="{ valid: !$v.client.clientLogo.$invalid, invalid: $v.client.clientLogo.$invalid }"
              v-model="$v.client.clientLogo.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="clientLogoContentType"
              id="client-clientLogoContentType"
              v-model="client.clientLogoContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.url')" for="client-url">Url</label>
            <input
              type="text"
              class="form-control"
              name="url"
              id="client-url"
              data-cy="url"
              :class="{ valid: !$v.client.url.$invalid, invalid: $v.client.url.$invalid }"
              v-model="$v.client.url.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.dateCreation')" for="client-dateCreation">Date Creation</label>
            <div class="d-flex">
              <input
                id="client-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.client.dateCreation.$invalid, invalid: $v.client.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.client.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.dateModify')" for="client-dateModify">Date Modify</label>
            <div class="d-flex">
              <input
                id="client-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.client.dateModify.$invalid, invalid: $v.client.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.client.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.lastModifyBy')" for="client-lastModifyBy">Last Modify By</label>
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="client-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.client.lastModifyBy.$invalid, invalid: $v.client.lastModifyBy.$invalid }"
              v-model="$v.client.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.createdBy')" for="client-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="client-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.client.createdBy.$invalid, invalid: $v.client.createdBy.$invalid }"
              v-model="$v.client.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.client.notes')" for="client-notes">Notes</label>
            <textarea
              class="form-control"
              name="notes"
              id="client-notes"
              data-cy="notes"
              :class="{ valid: !$v.client.notes.$invalid, invalid: $v.client.notes.$invalid }"
              v-model="$v.client.notes.$model"
            ></textarea>
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
            :disabled="$v.client.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./client-update.component.ts"></script>
