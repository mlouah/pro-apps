<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="proAppsApp.moM.home.createOrEditLabel"
          data-cy="MoMCreateUpdateHeading"
          v-text="$t('proAppsApp.moM.home.createOrEditLabel')"
        >
          Create or edit a MoM
        </h2>
        <div>
          <div class="form-group" v-if="moM.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="moM.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.title')" for="mo-m-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="mo-m-title"
              data-cy="title"
              :class="{ valid: !$v.moM.title.$invalid, invalid: $v.moM.title.$invalid }"
              v-model="$v.moM.title.$model"
              required
            />
            <div v-if="$v.moM.title.$anyDirty && $v.moM.title.$invalid">
              <small class="form-text text-danger" v-if="!$v.moM.title.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.meetingDate')" for="mo-m-meetingDate">Meeting Date</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="mo-m-meetingDate"
                  v-model="$v.moM.meetingDate.$model"
                  name="meetingDate"
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
                id="mo-m-meetingDate"
                data-cy="meetingDate"
                type="text"
                class="form-control"
                name="meetingDate"
                :class="{ valid: !$v.moM.meetingDate.$invalid, invalid: $v.moM.meetingDate.$invalid }"
                v-model="$v.moM.meetingDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.minutesWritingDate')" for="mo-m-minutesWritingDate"
              >Minutes Writing Date</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="mo-m-minutesWritingDate"
                  v-model="$v.moM.minutesWritingDate.$model"
                  name="minutesWritingDate"
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
                id="mo-m-minutesWritingDate"
                data-cy="minutesWritingDate"
                type="text"
                class="form-control"
                name="minutesWritingDate"
                :class="{ valid: !$v.moM.minutesWritingDate.$invalid, invalid: $v.moM.minutesWritingDate.$invalid }"
                v-model="$v.moM.minutesWritingDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.meetingObjectif')" for="mo-m-meetingObjectif"
              >Meeting Objectif</label
            >
            <textarea
              class="form-control"
              name="meetingObjectif"
              id="mo-m-meetingObjectif"
              data-cy="meetingObjectif"
              :class="{ valid: !$v.moM.meetingObjectif.$invalid, invalid: $v.moM.meetingObjectif.$invalid }"
              v-model="$v.moM.meetingObjectif.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.meetingDocReferences')" for="mo-m-meetingDocReferences"
              >Meeting Doc References</label
            >
            <textarea
              class="form-control"
              name="meetingDocReferences"
              id="mo-m-meetingDocReferences"
              data-cy="meetingDocReferences"
              :class="{ valid: !$v.moM.meetingDocReferences.$invalid, invalid: $v.moM.meetingDocReferences.$invalid }"
              v-model="$v.moM.meetingDocReferences.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.isMoMActionsClosed')" for="mo-m-isMoMActionsClosed"
              >Is Mo M Actions Closed</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="isMoMActionsClosed"
              id="mo-m-isMoMActionsClosed"
              data-cy="isMoMActionsClosed"
              :class="{ valid: !$v.moM.isMoMActionsClosed.$invalid, invalid: $v.moM.isMoMActionsClosed.$invalid }"
              v-model="$v.moM.isMoMActionsClosed.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.subjectDecisions')" for="mo-m-subjectDecisions"
              >Subject Decisions</label
            >
            <textarea
              class="form-control"
              name="subjectDecisions"
              id="mo-m-subjectDecisions"
              data-cy="subjectDecisions"
              :class="{ valid: !$v.moM.subjectDecisions.$invalid, invalid: $v.moM.subjectDecisions.$invalid }"
              v-model="$v.moM.subjectDecisions.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.meetingPhotoCapture')" for="mo-m-meetingPhotoCapture"
              >Meeting Photo Capture</label
            >
            <div>
              <img
                v-bind:src="'data:' + moM.meetingPhotoCaptureContentType + ';base64,' + moM.meetingPhotoCapture"
                style="max-height: 100px"
                v-if="moM.meetingPhotoCapture"
                alt="moM image"
              />
              <div v-if="moM.meetingPhotoCapture" class="form-text text-danger clearfix">
                <span class="pull-left">{{ moM.meetingPhotoCaptureContentType }}, {{ byteSize(moM.meetingPhotoCapture) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('meetingPhotoCapture', 'meetingPhotoCaptureContentType', 'file_meetingPhotoCapture')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_meetingPhotoCapture"
                id="file_meetingPhotoCapture"
                data-cy="meetingPhotoCapture"
                v-on:change="setFileData($event, moM, 'meetingPhotoCapture', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="meetingPhotoCapture"
              id="mo-m-meetingPhotoCapture"
              data-cy="meetingPhotoCapture"
              :class="{ valid: !$v.moM.meetingPhotoCapture.$invalid, invalid: $v.moM.meetingPhotoCapture.$invalid }"
              v-model="$v.moM.meetingPhotoCapture.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="meetingPhotoCaptureContentType"
              id="mo-m-meetingPhotoCaptureContentType"
              v-model="moM.meetingPhotoCaptureContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.dateCreation')" for="mo-m-dateCreation">Date Creation</label>
            <div class="d-flex">
              <input
                id="mo-m-dateCreation"
                data-cy="dateCreation"
                type="datetime-local"
                class="form-control"
                name="dateCreation"
                :class="{ valid: !$v.moM.dateCreation.$invalid, invalid: $v.moM.dateCreation.$invalid }"
                :value="convertDateTimeFromServer($v.moM.dateCreation.$model)"
                @change="updateInstantField('dateCreation', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.dateModify')" for="mo-m-dateModify">Date Modify</label>
            <div class="d-flex">
              <input
                id="mo-m-dateModify"
                data-cy="dateModify"
                type="datetime-local"
                class="form-control"
                name="dateModify"
                :class="{ valid: !$v.moM.dateModify.$invalid, invalid: $v.moM.dateModify.$invalid }"
                :value="convertDateTimeFromServer($v.moM.dateModify.$model)"
                @change="updateInstantField('dateModify', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.lastModifyBy')" for="mo-m-lastModifyBy">Last Modify By</label>
            <input
              type="text"
              class="form-control"
              name="lastModifyBy"
              id="mo-m-lastModifyBy"
              data-cy="lastModifyBy"
              :class="{ valid: !$v.moM.lastModifyBy.$invalid, invalid: $v.moM.lastModifyBy.$invalid }"
              v-model="$v.moM.lastModifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.createdBy')" for="mo-m-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="mo-m-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.moM.createdBy.$invalid, invalid: $v.moM.createdBy.$invalid }"
              v-model="$v.moM.createdBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.attendees')" for="mo-m-attendees">Attendees</label>
            <textarea
              class="form-control"
              name="attendees"
              id="mo-m-attendees"
              data-cy="attendees"
              :class="{ valid: !$v.moM.attendees.$invalid, invalid: $v.moM.attendees.$invalid }"
              v-model="$v.moM.attendees.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.notes')" for="mo-m-notes">Notes</label>
            <textarea
              class="form-control"
              name="notes"
              id="mo-m-notes"
              data-cy="notes"
              :class="{ valid: !$v.moM.notes.$invalid, invalid: $v.moM.notes.$invalid }"
              v-model="$v.moM.notes.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('proAppsApp.moM.projet')" for="mo-m-projet">Projet</label>
            <select class="form-control" id="mo-m-projet" data-cy="projet" name="projet" v-model="moM.projet">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="moM.projet && projectOption.id === moM.projet.id ? moM.projet : projectOption"
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.code }}
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
            :disabled="$v.moM.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./mo-m-update.component.ts"></script>
