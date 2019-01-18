/*
 * Ported from javascript project -
 * https://github.com/mikemclin/angular-acl/blob/master/src/angular-acl.js
 */

import * as _ from 'lodash';
import {Injectable} from '@angular/core';
import {AUTH_ACL} from '../core/auth/auth.constant';

@Injectable()
export class AuthorizationService {

    config: any = {
        storage: 'localStorage',
        storageKey: 'AuthorizationService',
    };

    data: any = {
        roles: [],
        abilities: {},
    };

    constructor() {
        // no op
    }

    /**
     * Does the given role have abilities granted to it?
     *
     * @param role
     * @returns {boolean}
     */
    roleHasAbilities(role): boolean {
        return (typeof this.data.abilities[role] === 'object');
    }

    /**
     * Retrieve the abilities array for the given role
     *
     * @param role
     * @returns {Array}
     */
    getRoleAbilities(role): any[] {
        return (this.roleHasAbilities(role)) ? this.data.abilities[role] : [];
    }

    /**
     * Restore data from web storage.
     *
     * Returns true if web storage exists and false if it doesn't.
     *
     * @returns {boolean}
     */
    resume() {
        let storedData;

        switch (this.config.storage) {
            case 'sessionStorage':
                storedData = this.fetchFromStorage('sessionStorage');
                break;
            case 'localStorage':
                storedData = this.fetchFromStorage('localStorage');
                break;
            default:
                storedData = null;
        }
        if (storedData) {
            _.extend(this.data, storedData);
            return true;
        }

        return false;
    }

    /**
     * Attach a role to the current user
     *
     * @param role
     */
    attachRole(role) {
        if (this.data.roles.indexOf(role) === -1) {
            this.data.roles.push(role);
            this.save();
        }
    }

    /**
     * Remove role from current user
     *
     * @param role
     */
    detachRole(role) {
        const i: number = this.data.roles.indexOf(role);
        if (i > -1) {
            this.data.roles.splice(i, 1);
            this.save();
        }
    }

    /**
     * Remove all roles from current user
     */
    flushRoles(): void {
        this.data.roles = [];
        this.data.abilities = {};
        this.save();
    }

    /**
     * Check if the current user has role attached
     *
     * @param role
     * @returns {boolean}
     */
    hasRole(role: string): boolean {
        const x = window.localStorage.getItem('authorization');
        const authObj = JSON.parse(x);

        if (authObj) {
            const roles = authObj.roles;
            return (roles.includes(role));
        } else {
            return false;
        }
    }

    /**
     * Returns the current user roles
     * @returns {Array}
     */
    getRoles() {
        return this.data.roles;
    }

    /**
     * Set the abilities object (overwriting previous abilities)
     *
     * Each property on the abilities object should be a role.
     * Each role should have a value of an array. The array should contain
     * a list of all of the roles abilities.
     *
     * Example:
     *
     *    {
     *        guest: ['login'],
     *        user: ['logout', 'view_content'],
     *        admin: ['logout', 'view_content', 'manage_users']
     *    }
     *
     * @param abilities
     */
    setAbilities(abilities) {
        this.data.abilities = abilities;
        this.save();
    }

    /**
     * Add an ability to a role
     *
     * @param role
     * @param ability
     */
    addAbility(role, ability) {
        if (!this.data.abilities[role]) {
            this.data.abilities[role] = [];
        }
        this.data.abilities[role].push(ability);
        this.save();
    }

    /**
     * Toggle ability of a role
     * If role is empty, current current roles are used
     *
     * @param role
     * @param ability
     * @param [force] - force add or remove
     */
    toggleAbility(role, ability, force) {
        const roles = role ? [role] : this.data.roles;

        // Loop through roles
        for (let i = 0; i < roles.length; i++) {
            const role = roles[i];

            if (!this.data.abilities[role]) {
                this.data.abilities[role] = [];
            }

            const alreadyAdded = _.indexOf(this.data.abilities[role], ability) !== -1;
            let operation = !alreadyAdded;

            if (!alreadyAdded && force === true) {
                operation = true;
            }

            if (alreadyAdded && force === false) {
                operation = false;
            }

            if (operation === true) {
                this.data.abilities[role].push(ability);
            }

            if (operation === false) {
                this.data.abilities[role] = _.without(this.data.abilities[role], ability);
            }
        }

        this.save();
    }

    /**
     * Does current user have permission to do something?
     *
     * @param ability
     * @returns {boolean}
     */
    can(ability) {
        let role, abilities;
        // Loop through roles
        let l: number = this.data.roles.length;
        for (; l--;) {
            // Grab the the current role
            role = this.data.roles[l];
            abilities = this.getRoleAbilities(role);
            if (abilities.indexOf(ability) > -1) {
                // Ability is in role abilities
                return true;
            }
        }
        // We made it here, so the ability wasn't found in attached roles
        return false;
    }

    /**
     * Does current user have any of the required permission to do something?
     *
     * @param abilities [array]
     * @returns {boolean}
     */
    canAny(abilities) {
        let role, roleAbilities;
        // Loop through roles
        let l = this.data.roles.length;
        let j = abilities.length;

        for (; l--;) {
            // Grab the the current role
            role = this.data.roles[l];
            roleAbilities = this.getRoleAbilities(role);

            for (; j--;) {
                if (roleAbilities.indexOf(abilities[j]) > -1) {
                    // Ability is in role abilities
                    return true;
                }
            }
        }
        // We made it here, so the ability wasn't found in attached roles
        return false;
    }

    /**
     * Persist data to storage based on config
     */
    private save(): void {
        switch (this.config.storage) {
            case 'sessionStorage':
                this.saveToStorage('sessionStorage');
                break;
            case 'localStorage':
                this.saveToStorage('localStorage');
                break;
            default:
                // Don't save
                return;
        }
    }

    /**
     * Persist data to web storage
     */
    private saveToStorage(storagetype): void {
        window[storagetype]['setItem'](AUTH_ACL, JSON.stringify(this.data));
    }

    /**
     * Retrieve data from web storage
     */
    private fetchFromStorage(storagetype): any {
        const data = window[storagetype]['getItem'](AUTH_ACL);
        return (data) ? JSON.parse(data) : false;
    }
}
